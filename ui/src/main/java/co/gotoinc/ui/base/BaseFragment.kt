package co.gotoinc.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import co.gotoinc.core.Logger
import co.gotoinc.domain.AirplaneProgressBarInteractor
import co.gotoinc.ui.R
import co.gotoinc.ui.view.airports.AirportsFragment
import co.gotoinc.ui.view.airports.AirportsViewModel
import co.gotoinc.ui.view.book_flight.BookFragment
import co.gotoinc.ui.view.book_flight.BookViewModel
import co.gotoinc.ui.view.flight_date.FlightDateFragment
import co.gotoinc.ui.view.flight_date.FlightDateViewModel
import co.gotoinc.ui.view.passengers.PassengersFragment
import co.gotoinc.ui.view.passengers.PassengersViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel


abstract class BaseFragment<ViewModel : BaseViewModel> : Fragment() {
    private val airplaneProgressBarInteractor: AirplaneProgressBarInteractor by lazy { activity as AirplaneProgressBarInteractor }
    protected val logger: Logger by inject()
    protected val viewModel: ViewModel
        get() {
            return when (javaClass) {
                BookFragment::class.java -> getViewModel<BookViewModel>()
                AirportsFragment::class.java -> getViewModel<AirportsViewModel>()
                PassengersFragment::class.java -> getViewModel<PassengersViewModel>()
                FlightDateFragment::class.java -> getViewModel<FlightDateViewModel>()
                else -> throw IllegalArgumentException()
            } as ViewModel
        }

    private fun navController() =
        activity?.let { Navigation.findNavController(it, R.id.fragmentContainer) }

    protected fun navigateTo(id: Int, bundle: Bundle? = null) =
        navController()?.navigate(id, bundle)

    protected fun back() = navController()?.navigateUp()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    protected fun showProgress(show: Boolean) = airplaneProgressBarInteractor.showProgress(show)

    protected abstract fun getLayoutId(): Int

    protected fun getColor(id: Int) = context?.resources?.getColor(id)

    protected fun showMaterialDialog(
        title: String? = null,
        description: String? = null,
        ok: () -> Unit = { },
        no: () -> Unit = { }
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(description)
            .setPositiveButton(R.string.yes) { _, _ -> ok() }
            .setNegativeButton(R.string.no) { _, _ -> no() }
            .show()
    }

    protected fun showMaterialDialog(
        title: Int? = null,
        description: Int? = null,
        ok: () -> Unit = { },
        no: () -> Unit = { }
    ) {
        showMaterialDialog(
            if (title != null) getString(title) else null,
            if (description != null) getString(description) else null,
            ok,
            no
        )
    }

    protected fun showSimpleMaterialDialog(title: Int) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setPositiveButton(R.string.yes, null)
            .show()
    }
}