package co.gotoinc.ui.base

import androidx.fragment.app.Fragment
import co.gotoinc.ui.view.book_flight.BookFragment
import co.gotoinc.ui.view.book_flight.BookViewModel
import org.koin.android.viewmodel.ext.android.getViewModel

abstract class BaseFragment<ViewModel : BaseViewModel> : Fragment() {
    protected val viewModel: ViewModel
        get() {
            return when (javaClass) {
                BookFragment::class.java -> getViewModel<BookViewModel>()
                else -> throw IllegalArgumentException()
            } as ViewModel
        }
}