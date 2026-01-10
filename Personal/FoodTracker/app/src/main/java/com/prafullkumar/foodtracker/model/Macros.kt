package com.prafullkumar.foodtracker.model

import com.google.gson.annotations.SerializedName

data class Macros(
    @SerializedName("carbohydrate_g") val carbohydrate_g: Double,
    @SerializedName("fat_g") val fat_g: Double,
    @SerializedName("fiber_g") val fiber_g: Int,
    @SerializedName("protein_g") val protein_g: Double,
    @SerializedName("sugars_g") val sugars_g: Double
)