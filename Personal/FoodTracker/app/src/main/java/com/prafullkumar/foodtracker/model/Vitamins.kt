package com.prafullkumar.foodtracker.model

import com.google.gson.annotations.SerializedName

data class Vitamins(
    @SerializedName("biotin_b7_ug") val biotin_b7_ug: Double,
    @SerializedName("folate_b9_dfe_ug") val folate_b9_dfe_ug: Int,
    @SerializedName("niacin_b3_mg") val niacin_b3_mg: Double,
    @SerializedName("pantothenic_acid_b5_mg") val pantothenic_acid_b5_mg: Double,
    @SerializedName("riboflavin_b2_mg") val riboflavin_b2_mg: Double,
    @SerializedName("thiamin_b1_mg") val thiamin_b1_mg: Double,
    @SerializedName("vitamin_a_rae_ug") val vitamin_a_rae_ug: Int,
    @SerializedName("vitamin_b12_ug") val vitamin_b12_ug: Double,
    @SerializedName("vitamin_b6_mg") val vitamin_b6_mg: Double,
    @SerializedName("vitamin_c_mg") val vitamin_c_mg: Int,
    @SerializedName("vitamin_d_ug") val vitamin_d_ug: Double,
    @SerializedName("vitamin_e_mg") val vitamin_e_mg: Double,
    @SerializedName("vitamin_k_ug") val vitamin_k_ug: Double
)