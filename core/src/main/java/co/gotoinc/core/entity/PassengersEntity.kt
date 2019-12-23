package co.gotoinc.core.entity

data class PassengersEntity(
    var adults: Int = 1,
    var childs: Int = 0,
    var infants: Int = 0
) {
    fun increment(field: Fields) {
        when (field) {
            Fields.ADULTS -> if (adults < 10) adults++
            Fields.CHILDS -> if (childs < 10) childs++
            Fields.INFANTS -> if (infants < 10) infants++
        }
    }

    fun decrement(field: Fields) {
        when (field) {
            Fields.ADULTS -> if (adults > 0) adults--
            Fields.CHILDS -> if (childs > 0) childs--
            Fields.INFANTS -> if (infants > 0) infants--
        }
    }

    enum class Fields {
        ADULTS, CHILDS, INFANTS
    }
}