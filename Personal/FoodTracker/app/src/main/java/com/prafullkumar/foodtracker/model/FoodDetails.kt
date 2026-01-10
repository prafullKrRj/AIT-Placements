package com.prafullkumar.foodtracker.model

import com.google.gson.annotations.SerializedName


data class FoodDetails(
    @SerializedName("amino_acids")
    val amino_acids: AminoAcids,
    @SerializedName("confidence")
    val confidence: Confidence,
    @SerializedName("energy")
    val energy: Energy,
    @SerializedName("food_id")
    val food_id: String,
    @SerializedName("food_name")
    val food_name: String,
    @SerializedName("form")
    val form: String,
    @SerializedName("last_verified")
    val last_verified: String,
    @SerializedName("lipids")
    val lipids: Lipids,
    @SerializedName("macros")
    val macros: Macros,
    @SerializedName("minerals")
    val minerals: Minerals,
    @SerializedName("other_compounds")
    val other_compounds: OtherCompounds,
    @SerializedName("serving_basis")
    val serving_basis: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("vitamins")
    val vitamins: Vitamins
)