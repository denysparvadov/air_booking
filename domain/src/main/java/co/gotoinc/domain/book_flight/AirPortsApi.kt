package co.gotoinc.domain.book_flight

import co.gotoinc.core.entity.Airport

interface AirPortsApi {
    @Throws(Throwable::class)
    suspend fun getAirports(): List<Airport>?
}