package co.gotoinc.core.entity

import co.gotoinc.core.ListItem
import com.google.gson.annotations.SerializedName

data class Airport(
    @SerializedName("name")
    val name: String,
    @SerializedName("iata_code")
    val code: String?,
    @SerializedName("iso_country")
    val countryIsoCode: String,
    @SerializedName("municipality")
    val city: String
) : ListItem()