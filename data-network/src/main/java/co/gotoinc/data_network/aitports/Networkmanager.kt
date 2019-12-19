package co.gotoinc.data_network.aitports

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Networkmanager {
    private val retrofit: Retrofit
    val airportsApi: AirPortsApi

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://pkgstore.datahub.io/core/airport-codes/airport-codes_json/data/5b49454904942ea4e6789d5100922bc1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }).build())
            .build()
        airportsApi = retrofit.create(AirPortsApi::class.java)
    }
}