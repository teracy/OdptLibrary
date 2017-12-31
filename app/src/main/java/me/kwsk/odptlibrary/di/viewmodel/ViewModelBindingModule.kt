package me.kwsk.odptlibrary.di.viewmodel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.kwsk.odptlibrary.viewmodel.MainViewModel
import me.kwsk.odptlibrary.viewmodel.MainViewModelComponent

// TODO: Modify @Module annotation accordingly
@Module(subcomponents = arrayOf(MainViewModelComponent::class))
abstract class ViewModelBindingModule {
    @Binds
    @IntoMap
    @ViewModelMapKey(MainViewModel::class)
    abstract fun sampleViewModelComponentBuilder(builder: MainViewModelComponent.Builder): ViewModelComponentBuilder<*, *>

    // NOTE: Add injection maps here
}
