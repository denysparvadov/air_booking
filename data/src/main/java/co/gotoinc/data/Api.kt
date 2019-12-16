package co.gotoinc.data

import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("airport-codes_json")
    suspend fun getAIrports(): Response<List<Airport>>
}