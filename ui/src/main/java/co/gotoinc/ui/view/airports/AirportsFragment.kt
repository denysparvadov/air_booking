package co.gotoinc.ui.view.airports

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isGone
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import co.gotoinc.core.entity.Airport
import co.gotoinc.core.hideKeyboard
import co.gotoinc.ui.R
import co.gotoinc.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_airports.*

private const val ORIGIN = "ORIGIN"
private const val DESTINATION = "DESTINATION"
private const val TYPE = "TYPE"

class AirportsFragment : BaseFragment<AirportsViewModel>() {
    private val adapter: AirportsAdapter by lazy {
        AirportsAdapter { airport ->
            state.onItemClick(
                airport
            )
        }
    }
    private lateinit var state: State

    companion object {
        fun getOriginBundle() = Bundle().apply { putString(TYPE, ORIGIN) }
        fun getDestinationBundle() = Bundle().apply { putString(TYPE, DESTINATION) }
    }

    override fun getLayoutId() = R.layout.fragment_airports

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        state = when (arguments?.getString(TYPE)) {
            ORIGIN -> OriginState()
            DESTINATION -> DestinationState()
            else -> throw IllegalArgumentException("Should have ORIGIN or DESTINATION type")
        }
        viewModel.observeAirports().observe(this, Observer { state ->
            editTextSearch.isEnabled = !state.isLoading
            showProgress(state.isLoading)
            when {
                state.data != null -> {
                    viewModel.airports = state.data!!
                    adapter.submitList(null)
                    adapter.submitList(viewModel.getPagedList())
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        editTextSearch.doAfterTextChanged { editable ->
            buttonClearSearch.isGone = editable!!.isEmpty()
        }
        buttonClearSearch.setOnClickListener { clearSearch() }
        editTextSearch.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                adapter.submitList(null)
                viewModel.searchAirports(v.text.toString())
            }
            true
        }
    }

    private fun clearSearch() {
        viewModel.searchAirports()
        adapter.submitList(null)
        hideKeyboard()
        editTextSearch.setText("")
    }

    private interface State {
        fun onItemClick(airport: Airport)
    }

    private inner class OriginState : State {
        override fun onItemClick(airport: Airport) {
            viewModel.setOrigin(airport)
            back()
        }
    }

    private inner class DestinationState : State {
        override fun onItemClick(airport: Airport) {
            viewModel.setDestination(airport)
            back()
        }
    }
}