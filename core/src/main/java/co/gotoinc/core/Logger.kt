package co.gotoinc.core

import android.util.Log

class Logger {
    private val tag = "MyTag"
    fun log(message: String) {
        Log.d(tag, message)
    }
}