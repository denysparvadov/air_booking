package co.gotoinc.gotoair

import android.app.Application
import co.gotoinc.di.appModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(context = this, modules = listOf(appModule))
    }
}