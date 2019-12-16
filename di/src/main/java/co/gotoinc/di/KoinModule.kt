package co.gotoinc.di

import co.gotoinc.data.Networkmanager
import co.gotoinc.ui.view.book_flight.BookViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { BookViewModel() }
    single { Networkmanager() }
}