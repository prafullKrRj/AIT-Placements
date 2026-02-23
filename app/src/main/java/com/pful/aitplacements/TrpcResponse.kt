package com.pful.aitplacements

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// --- Models ---

// tRPC returns an array, but the inner object follows this structure
data class TrpcResponse<T>(
    val result: TrpcResult<T>
)

data class TrpcResult<T>(
    val data: T
)

// List Response Models
data class NoticeListResponse(
    val totalNotice: Int,
    val notices: List<NoticeItem>
)

data class NoticeItem(
    val id: String,
    val title: String,
    val admin: String,
    val updatedAt: String
)

// Detail Response Models
data class NoticeDetail(
    val id: String,
    val isPublished: Boolean,
    val title: String,
    val body: String?, // HTML content
    val attachments: List<Attachment>?
)

data class Attachment(
    val url: String,
    val name: String,
    val type: String
)

private val trpcGson = Gson()
private const val DEFAULT_NOTICE_PAGE = 1
private const val BATCH_DETAIL_INDEX = 1

internal fun createNoticeListInput(page: Int): String =
    trpcGson.toJson(mapOf("0" to mapOf("pageNos" to page)))

internal fun createNoticeDetailInput(id: String): String =
    trpcGson.toJson(mapOf("0" to mapOf("id" to id)))

internal fun createNoticePageInput(page: Int, id: String): String =
    trpcGson.toJson(
        linkedMapOf(
            "0" to mapOf("pageNos" to page),
            "1" to mapOf("id" to id)
        )
    )

// --- Preferences (Cookie Storage) ---
class UserPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveCookie(cookie: String) {
        prefs.edit().putString("auth_cookie", cookie).apply()
    }

    fun getCookie(): String? {
        return prefs.getString("auth_cookie", null)
    }

    fun clear() {
        prefs.edit().clear().apply()
    }
}

// --- API Service ---
interface AitApiService {
    
    // Using @Query to manually construct the weird tRPC JSON input format
    // URL: notice.publishedNoticeList?batch=1&input={"0":{"pageNos":X}}
    @GET("notice.publishedNoticeList?batch=1")
    suspend fun getNotices(
        @Query("input") inputJson: String
    ): List<TrpcResponse<NoticeListResponse>>

    // URL: notice.noticeDetail?batch=1&input={"0":{"id":"UUID"}}
    @GET("notice.noticeDetail?batch=1")
    suspend fun getNoticeDetail(
        @Query("input") inputJson: String
    ): List<TrpcResponse<NoticeDetail>>

    // URL: notice.publishedNoticeList,notice.noticeDetail?batch=1&input={"0":{"pageNos":X},"1":{"id":"UUID"}}
    @GET("notice.publishedNoticeList,notice.noticeDetail?batch=1")
    suspend fun getNoticePageData(
        @Query("input") inputJson: String
    ): List<TrpcResponse<JsonObject>>
}

// --- Repository (With Session Cache) ---
class NoticeRepository(
    private val api: AitApiService,
    private val prefs: UserPreferences
) {
    // Session Cache: Stores pages we've already fetched so we don't reload when switching pages
    private val _pageCache = mutableMapOf<Int, NoticeListResponse>()
    
    fun hasCookie(): Boolean = !prefs.getCookie().isNullOrBlank()

    suspend fun getNotices(page: Int): Result<NoticeListResponse> {
        return try {
            // Check cache first
            if (_pageCache.containsKey(page)) {
                return Result.success(_pageCache[page]!!)
            }

            val input = createNoticeListInput(page)
            
            val response = api.getNotices(input)
            
            // tRPC returns an array, we usually want the first item [0]
            val data = response.firstOrNull()?.result?.data
                ?: return Result.failure(IllegalStateException("Empty notice list response"))
            
            // Save to cache
            _pageCache[page] = data
            Result.success(data)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun getNoticeDetail(id: String): Result<NoticeDetail> {
        return try {
            val pageResponse = api.getNoticePageData(createNoticePageInput(DEFAULT_NOTICE_PAGE, id))
            val detailFromPage = pageResponse.getOrNull(BATCH_DETAIL_INDEX)?.result?.data?.let {
                trpcGson.fromJson(it, NoticeDetail::class.java)
            }
            if (detailFromPage != null) {
                return Result.success(detailFromPage)
            }

            val response = api.getNoticeDetail(createNoticeDetailInput(id))
            val detail = response.firstOrNull()?.result?.data
                ?: return Result.failure(IllegalStateException("Empty notice detail response"))
            Result.success(detail)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

// --- Network Setup ---
fun provideRetrofit(prefs: UserPreferences): Retrofit {
    val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    
    val cookieInterceptor = Interceptor { chain ->
        val original = chain.request()
        val cookie = prefs.getCookie()
        val requestBuilder = original.newBuilder()
        
        if (!cookie.isNullOrEmpty()) {
            requestBuilder.addHeader("Cookie", cookie)
        }
        
        chain.proceed(requestBuilder.build())
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(cookieInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl("https://www.aitplacements.in/api/trpc/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
