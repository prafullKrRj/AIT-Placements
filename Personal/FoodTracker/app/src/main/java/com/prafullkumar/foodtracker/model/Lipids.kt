package com.prafullkumar.foodtracker.model

import com.google.gson.annotations.SerializedName

data class Lipids(
    @SerializedName("cholesterol_mg") val cholesterol_mg: Int,
    @SerializedName("monounsaturated_fat_g") val monounsaturated_fat_g: Double,
    @SerializedName("polyunsaturated_fat_g") val polyunsaturated_fat_g: Double,
    @SerializedName("saturated_fat_g") val saturated_fat_g: Double
)