package me.kwsk.odptlibrary.di.viewmodel

import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import me.kwsk.odptlibrary.di.scope.ViewModelScope

@Module
abstract class ViewModelModule<out T : ViewModel>(protected val viewModel: T) {
    @Provides
    @ViewModelScope
    fun provideViewModel(): T {
        return viewModel
    }

    // NOTE: Add dependencies here
}
