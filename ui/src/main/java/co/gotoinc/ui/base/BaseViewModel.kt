package co.gotoinc.ui.base

import androidx.lifecycle.ViewModel
import co.gotoinc.core.Logger
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

abstract class BaseViewModel : ViewModel(), KoinComponent {
    val logger: Logger by inject()
}