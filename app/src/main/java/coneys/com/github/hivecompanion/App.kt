package coneys.com.github.hivecompanion

import android.app.Application
import coneys.com.github.hivecompanion.dependencyInjection.AppComponent
import coneys.com.github.hivecompanion.dependencyInjection.Injector

class App : Application() {

    companion object {
        @JvmStatic private lateinit var appComponent: AppComponent
        @JvmStatic lateinit var injector: Injector
        @JvmStatic
        var token = ""
        set(value) {
            println("UPDATED!")
            field="Bearer $value"
        }

    }

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.create(this)
        injector = Injector(appComponent)
    }
}