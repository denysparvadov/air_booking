package co.gotoinc.ui.base

import androidx.lifecycle.ViewModel
import co.gotoinc.ui.view.book_flight.BookViewModel

class ViewModelFactory<T : ViewModel> {
    fun getViewModel(viewModelClass: Class<T>): ViewModel {
        return when (viewModelClass) {
            BookViewModel::class.java -> BookViewModel()
            else -> throw IllegalArgumentException()
        }
    }
}