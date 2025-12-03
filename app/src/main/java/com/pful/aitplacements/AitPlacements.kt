package com.pful.aitplacements

import android.app.Application

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    // Singletons
    single { UserPreferences(androidContext()) }
    single { provideRetrofit(get()).create(AitApiService::class.java) }
    single { NoticeRepository(get(), get()) }

    // ViewModels
    viewModel { OnboardingViewModel(get()) }
    viewModel { NoticeListViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { (noticeId: String) -> NoticeDetailViewModel(noticeId, get()) }
}

class AitPlacements : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AitPlacements)
            modules(appModule)
        }
    }
}