package me.kwsk.odptlibrary

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import me.kwsk.odptlibrary.di.app.AppComponent
import me.kwsk.odptlibrary.di.app.AppModule
import me.kwsk.odptlibrary.di.app.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
    }
}
