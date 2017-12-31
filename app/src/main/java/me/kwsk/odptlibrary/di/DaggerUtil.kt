package me.kwsk.odptlibrary.di

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import me.kwsk.odptlibrary.App
import me.kwsk.odptlibrary.di.activity.ActivityComponentBuilder
import me.kwsk.odptlibrary.di.viewmodel.ViewModelComponentBuilder

class DaggerUtil {
    companion object {
        @Suppress("UNCHECKED_CAST")
        fun <T : ActivityComponentBuilder<*, *>> getComponentBuilder(activity: AppCompatActivity): T {
            return App.appComponent.activityComponentBuilders().get(activity.javaClass) as T
        }

        @Suppress("UNCHECKED_CAST")
        fun <T : ViewModelComponentBuilder<*, *>> getComponentBuilder(viewModel: ViewModel): T {
            return App.appComponent.viewModelComponentBuilders().get(viewModel.javaClass) as T
        }
    }
}
