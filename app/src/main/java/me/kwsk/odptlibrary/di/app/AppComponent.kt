package me.kwsk.odptlibrary.di.app

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import dagger.Component
import me.kwsk.odptlibrary.App
import me.kwsk.odptlibrary.di.activity.ActivityBindingModule
import me.kwsk.odptlibrary.di.activity.ActivityComponentBuilder
import me.kwsk.odptlibrary.di.viewmodel.ViewModelBindingModule
import me.kwsk.odptlibrary.di.viewmodel.ViewModelComponentBuilder
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, BuildTypeModule::class, ActivityBindingModule::class, ViewModelBindingModule::class))
interface AppComponent {
    fun inject(application: App)

    fun activityComponentBuilders(): Map<Class<out AppCompatActivity>, ActivityComponentBuilder<*, *>>

    fun viewModelComponentBuilders(): Map<Class<out ViewModel>, ViewModelComponentBuilder<*, *>>
}
