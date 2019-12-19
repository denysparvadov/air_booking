package co.gotoinc.data_network.aitports

import co.gotoinc.core.entity.Airport
import co.gotoinc.domain.book_flight.AirPortsApi

class AirPortsApiImpl(val networkManager: Networkmanager) : AirPortsApi {
    @Throws(Throwable::class)
    override suspend fun getAirports(): List<Airport>? {
        val response = networkManager.airportsApi.getAIrports()
        if (response.isSuccessful) return response.body()?.filter { !it.code.isNullOrEmpty() && !it.city.isNullOrEmpty() }
        else throw Throwable()
    }
}