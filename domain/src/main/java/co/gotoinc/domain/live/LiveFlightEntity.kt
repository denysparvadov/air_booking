package co.gotoinc.domain.live

import androidx.lifecycle.MutableLiveData
import co.gotoinc.core.entity.Airport
import co.gotoinc.core.entity.FlightEntity

class LiveFlightEntity(val flightEntity: FlightEntity = FlightEntity()) :
    MutableLiveData<FlightEntity>() {
    override fun getValue(): FlightEntity? {
        return flightEntity
    }

    fun updateOrigin(airport: Airport) {
        flightEntity.origin = airport
        super.setValue(flightEntity)
    }

    fun updateDestination(airport: Airport) {
        flightEntity.destination = airport
        super.setValue(flightEntity)
    }

    override fun setValue(value: FlightEntity?) {
    }


}