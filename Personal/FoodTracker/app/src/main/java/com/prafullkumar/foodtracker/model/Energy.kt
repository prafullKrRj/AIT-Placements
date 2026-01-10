package com.prafullkumar.foodtracker.model

import com.google.gson.annotations.SerializedName

data class Energy(
    @SerializedName("calories_kcal") val calories_kcal: Int
)