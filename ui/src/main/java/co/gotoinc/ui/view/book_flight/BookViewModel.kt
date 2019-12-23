package co.gotoinc.ui.view.book_flight

import android.view.View
import androidx.databinding.ObservableField
import co.gotoinc.core.entity.FlightEntity
import co.gotoinc.domain.live.LiveFlightEntity
import co.gotoinc.ui.base.BaseViewModel

class BookViewModel(
    private val liveFlightEntity: LiveFlightEntity
) : BaseViewModel() {
    lateinit var listener: View.OnClickListener
    var _flightEntity: ObservableField<FlightEntity> = ObservableField(FlightEntity())
    fun isReadyToSelectDate() = _flightEntity.get()?.isReadyToSelectDates() ?: false
    fun observeFlight() = liveFlightEntity

    fun setData(it: FlightEntity?) {
        _flightEntity.set(it ?: FlightEntity())
    }

    fun setDisplayPassengers(str: String) {
        displayPassengers.set(str)
    }

    var displayPassengers: ObservableField<String> = ObservableField<String>()
}