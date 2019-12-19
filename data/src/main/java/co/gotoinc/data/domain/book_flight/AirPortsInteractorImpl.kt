package co.gotoinc.data.domain.book_flight

import androidx.lifecycle.MutableLiveData
import co.gotoinc.core.State
import co.gotoinc.core.entity.Airport
import co.gotoinc.domain.book_flight.AirPortsApi
import co.gotoinc.domain.book_flight.AirPortsInteractor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AirPortsInteractorImpl(val airPortsApi: AirPortsApi) : AirPortsInteractor {
    private val airportsLiveData: MutableLiveData<State<List<Airport>>> = MutableLiveData()
    override fun getAirports(query: String): MutableLiveData<State<List<Airport>>> {
        GlobalScope.launch {
            val state = State<List<Airport>>()
            airportsLiveData.postValue(state.apply { isLoading = true })
            try {
                var list = airPortsApi.getAirports()
                if (query.isNotEmpty())
                    list = list?.filter { it.city.contains(query, true) or it.code!!.contains(query, true) }
                airportsLiveData.postValue(state.apply {
                    data = list ?: listOf()
                    isLoading = false
                })
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        return airportsLiveData
    }
}