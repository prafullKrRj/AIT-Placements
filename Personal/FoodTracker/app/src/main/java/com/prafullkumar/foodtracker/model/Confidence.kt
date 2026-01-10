package com.prafullkumar.foodtracker.model

import com.google.gson.annotations.SerializedName

data class Confidence(
    @SerializedName("amino_acids") val amino_acids: String,
    @SerializedName("macros") val macros: String,
    @SerializedName("micros") val micros: String,
    @SerializedName("notes") val notes: String
)