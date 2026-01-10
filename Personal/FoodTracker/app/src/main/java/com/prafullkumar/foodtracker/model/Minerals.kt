package com.prafullkumar.foodtracker.model

import com.google.gson.annotations.SerializedName

data class Minerals(
    @SerializedName("calcium_mg") val calcium_mg: Int,
    @SerializedName("chloride_mg") val chloride_mg: Int,
    @SerializedName("chromium_ug") val chromium_ug: Any,
    @SerializedName("copper_mg") val copper_mg: Double,
    @SerializedName("fluoride_mg") val fluoride_mg: Any,
    @SerializedName("iodine_ug") val iodine_ug: Int,
    @SerializedName("iron_mg") val iron_mg: Double,
    @SerializedName("magnesium_mg") val magnesium_mg: Int,
    @SerializedName("manganese_mg") val manganese_mg: Double,
    @SerializedName("molybdenum_ug") val molybdenum_ug: Any,
    @SerializedName("phosphorus_mg") val phosphorus_mg: Int,
    @SerializedName("potassium_mg") val potassium_mg: Int,
    @SerializedName("selenium_ug") val selenium_ug: Double,
    @SerializedName("sodium_mg") val sodium_mg: Int,
    @SerializedName("zinc_mg") val zinc_mg: Double
)