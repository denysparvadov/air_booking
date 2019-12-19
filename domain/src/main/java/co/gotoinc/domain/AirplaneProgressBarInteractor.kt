package co.gotoinc.domain

interface AirplaneProgressBarInteractor {
    fun showProgress(show: Boolean)

    var isLoading: Boolean
}