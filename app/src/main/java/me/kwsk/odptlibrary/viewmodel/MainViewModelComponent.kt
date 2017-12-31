package me.kwsk.odptlibrary.viewmodel

import dagger.Module
import dagger.Subcomponent
import me.kwsk.odptlibrary.di.scope.ViewModelScope
import me.kwsk.odptlibrary.di.viewmodel.ViewModelComponent
import me.kwsk.odptlibrary.di.viewmodel.ViewModelComponentBuilder
import me.kwsk.odptlibrary.di.viewmodel.ViewModelModule

@Subcomponent(modules = arrayOf(MainViewModelComponent.MainViewModelModule::class))
@ViewModelScope
interface MainViewModelComponent : ViewModelComponent<MainViewModel> {
    @Subcomponent.Builder
    interface Builder : ViewModelComponentBuilder<MainViewModelModule, MainViewModelComponent>

    // TODO: Add parameters
    @Module
    class MainViewModelModule internal constructor(viewModel: MainViewModel) : ViewModelModule<MainViewModel>(viewModel) {
        // NOTE: Add constructor injection here
    }
}
