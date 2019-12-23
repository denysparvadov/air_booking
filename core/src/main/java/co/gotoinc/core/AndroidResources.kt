package co.gotoinc.core

interface AndroidResources {
    fun getQuantityStrings(id: Int, vararg params: String): String
}