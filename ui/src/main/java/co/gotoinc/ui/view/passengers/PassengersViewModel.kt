package co.gotoinc.ui.view.passengers

import android.view.View
import androidx.databinding.ObservableField
import co.gotoinc.core.entity.PassengersEntity
import co.gotoinc.domain.live.LiveFlightEntity
import co.gotoinc.ui.base.BaseViewModel

class PassengersViewModel(private val liveFlightEntity: LiveFlightEntity) : BaseViewModel() {
    lateinit var clickListener: View.OnClickListener
    val value = liveFlightEntity.value?.passengers!!.copy()
    var passengersEntity = ObservableField(value)

    fun add(field: PassengersEntity.Fields) {
        value.increment(field)
        passengersEntity.notifyChange()
    }

    fun minus(field: PassengersEntity.Fields) {
        value.decrement(field)
        passengersEntity.notifyChange()
    }

    fun applyChanges() {
        liveFlightEntity.updatePassengers(value)
    }
}