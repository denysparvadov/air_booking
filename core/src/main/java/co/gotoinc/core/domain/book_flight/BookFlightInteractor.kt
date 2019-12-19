package co.gotoinc.core.domain.book_flight

import co.gotoinc.core.State
import co.gotoinc.core.entity.Airport

interface BookFlightInteractor {
    fun getAirports(): State<List<Airport>>
}