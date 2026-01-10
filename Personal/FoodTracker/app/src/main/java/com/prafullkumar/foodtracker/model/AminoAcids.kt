package com.prafullkumar.foodtracker.model

import com.google.gson.annotations.SerializedName

data class AminoAcids(
    @SerializedName("alanine_g") val alanine_g: Double,
    @SerializedName("arginine_g") val arginine_g: Double,
    @SerializedName("aspartic_acid_g") val aspartic_acid_g: Double,
    @SerializedName("cysteine_g") val cysteine_g: Double,
    @SerializedName("glutamic_acid_g") val glutamic_acid_g: Double,
    @SerializedName("glycine_g") val glycine_g: Double,
    @SerializedName("histidine_g") val histidine_g: Double,
    @SerializedName("isoleucine_g") val isoleucine_g: Double,
    @SerializedName("leucine_g") val leucine_g: Double,
    @SerializedName("lysine_g") val lysine_g: Double,
    @SerializedName("methionine_g") val methionine_g: Double,
    @SerializedName("phenylalanine_g") val phenylalanine_g: Double,
    @SerializedName("proline_g") val proline_g: Double,
    @SerializedName("serine_g") val serine_g: Double,
    @SerializedName("threonine_g") val threonine_g: Double,
    @SerializedName("tryptophan_g") val tryptophan_g: Double,
    @SerializedName("tyrosine_g") val tyrosine_g: Double,
    @SerializedName("valine_g") val valine_g: Double
)