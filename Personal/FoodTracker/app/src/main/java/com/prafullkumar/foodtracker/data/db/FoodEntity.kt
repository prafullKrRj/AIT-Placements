package com.prafullkumar.foodtracker.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.prafullkumar.foodtracker.model.FoodDetails

@Entity
@TypeConverters(FoodDetailsConverter::class)
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val foodNamer: String,
    val foodDetails: FoodDetails
)

class FoodDetailsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromFoodDetails(foodDetails: FoodDetails): String {
        return gson.toJson(foodDetails)
    }

    @TypeConverter
    fun toFoodDetails(data: String): FoodDetails {
        return gson.fromJson(data, FoodDetails::class.java)
    }
}