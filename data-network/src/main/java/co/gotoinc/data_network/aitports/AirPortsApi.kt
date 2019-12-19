package co.gotoinc.data_network.aitports

import co.gotoinc.core.entity.Airport
import retrofit2.Response
import retrofit2.http.GET

interface AirPortsApi {
    @GET("airport-codes_json.json")
    suspend fun getAIrports(): Response<List<Airport>>
}