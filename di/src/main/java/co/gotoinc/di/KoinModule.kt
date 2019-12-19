package co.gotoinc.di

import co.gotoinc.core.Logger
import co.gotoinc.data.domain.book_flight.AirPortsInteractorImpl
import co.gotoinc.domain.live.LiveFlightEntity
import co.gotoinc.data_network.aitports.AirPortsApiImpl
import co.gotoinc.data_network.aitports.Networkmanager
import co.gotoinc.ui.view.airports.AirportsViewModel
import co.gotoinc.ui.view.book_flight.BookViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { Networkmanager() }
    single { AirPortsApiImpl(get()) }
    single { AirPortsInteractorImpl(get()) }
    single { Logger() }
    single { LiveFlightEntity() }

    viewModel { BookViewModel(get()) }
    viewModel { AirportsViewModel(get(), AirPortsInteractorImpl(AirPortsApiImpl(get()))) }
}