package co.gotoinc.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Networkmanager {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://pkgstore.datahub.io/core/airport-codes/airport-codes_json/data/5b49454904942ea4e6789d5100922bc1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}