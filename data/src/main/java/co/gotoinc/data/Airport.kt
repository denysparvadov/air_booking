package co.gotoinc.data

import com.google.gson.annotations.SerializedName

data class Airport(
    @SerializedName("name")
    val name: String,
    @SerializedName("iata_code")
    val code: String
)