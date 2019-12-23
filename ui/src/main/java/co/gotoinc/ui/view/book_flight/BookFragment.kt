package co.gotoinc.ui.view.book_flight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import co.gotoinc.core.entity.FlightEntity
import co.gotoinc.ui.R
import co.gotoinc.ui.base.BaseFragment
import co.gotoinc.ui.databinding.FragmentBookBinding
import co.gotoinc.ui.view.airports.AirportsFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class BookFragment : BaseFragment<BookViewModel>(), View.OnClickListener {
    private lateinit var binding: FragmentBookBinding

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.originContainer, R.id.origin -> navigateTo(
                R.id.action_airportsFragment,
                AirportsFragment.getOriginBundle()
            )
            R.id.destinationContainer, R.id.destination -> navigateTo(
                R.id.action_airportsFragment,
                AirportsFragment.getDestinationBundle()
            )
            R.id.newSearchButton -> {
                MaterialAlertDialogBuilder(context)
                    .setTitle(R.string.are_u_sure)
                    .setPositiveButton(R.string.yes) { _, _ -> setData(FlightEntity()) }
                    .setNegativeButton(R.string.no, null)
                    .show()
            }
            R.id.passengersContainer -> navigateTo(R.id.action_passengersTitle)
        }
    }

    override fun getLayoutId() = R.layout.fragment_book

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentBookBinding>(
            inflater,
            getLayoutId(),
            container,
            false
        ).apply {
            viewModel = this@BookFragment.viewModel
            binding = this
        }
            .root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.listener = this
        viewModel.observeFlight().observe(this, Observer(::setData))
        setData(FlightEntity())
    }

    private fun setData(it: FlightEntity?) {
        viewModel.setData(it)
        viewModel.setDisplayPassengers("")
        it?.let {
            viewModel.setDisplayPassengers(run {
                val strBuilder = StringBuilder()
                if (it.passengers.adults > 0) {
                    strBuilder.append(
                        resources.getQuantityString(
                            R.plurals.adults_plural,
                            it.passengers.adults,
                            it.passengers.adults
                        )
                    )
                    strBuilder.append(", ")
                }
                if (it.passengers.childs > 0) {
                    strBuilder.append(
                        resources.getQuantityString(
                            R.plurals.childs_plural,
                            it.passengers.childs,
                            it.passengers.childs
                        )
                    )
                    strBuilder.append(", ")
                }
                if (it.passengers.infants > 0) {
                    strBuilder.append(
                        resources.getQuantityString(
                            R.plurals.infants_plural,
                            it.passengers.infants,
                            it.passengers.infants
                        )
                    )
                    strBuilder.append(", ")
                }
                if (strBuilder.startsWith(" ")) strBuilder.deleteCharAt(0)
                if (strBuilder.startsWith(",")) strBuilder.deleteCharAt(0)
                if (strBuilder.endsWith(" ")) strBuilder.deleteCharAt(strBuilder.length - 1)
                if (strBuilder.endsWith(",")) strBuilder.deleteCharAt(strBuilder.length - 1)
                strBuilder.toString()
            })
        }
    }
}
