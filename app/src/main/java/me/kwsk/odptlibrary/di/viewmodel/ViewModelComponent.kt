package me.kwsk.odptlibrary.di.viewmodel

import android.arch.lifecycle.ViewModel
import dagger.MembersInjector

interface ViewModelComponent<T : ViewModel> : MembersInjector<T>

interface ViewModelComponentBuilder<M : ViewModelModule<*>, C : ViewModelComponent<*>> {
    fun getBuilder(viewModelModule: M): ViewModelComponentBuilder<M, C>
    fun build(): C
}
