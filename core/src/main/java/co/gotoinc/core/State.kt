package co.gotoinc.core

class State<Data> {
    var data: Data? = null
    var isLoading: Boolean = false
    var error: Throwable? = null
}