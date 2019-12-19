package co.gotoinc.domain.book_flight

import androidx.lifecycle.MutableLiveData
import co.gotoinc.core.State
import co.gotoinc.core.entity.Airport

interface AirPortsInteractor {
    fun getAirports(query: String = ""): MutableLiveData<State<List<Airport>>>
}