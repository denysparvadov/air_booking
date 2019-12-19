package co.gotoinc.core.entity

class FlightEntity {
    var origin: Airport? = null
    var destination: Airport? = null

    fun getDisplayOrigin() = run {
        origin?.let { return@run "${origin?.city} ${origin?.code}" }
        ""
    }

    fun getDisplayDestination() = run {
        destination?.let { return@run "${destination?.city} ${destination?.code}" }
        ""
    }
}