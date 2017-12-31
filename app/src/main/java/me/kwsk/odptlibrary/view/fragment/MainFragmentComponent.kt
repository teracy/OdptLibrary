package me.kwsk.odptlibrary.view.fragment

import dagger.Module
import dagger.Subcomponent
import me.kwsk.odptlibrary.di.fragment.FragmentComponent
import me.kwsk.odptlibrary.di.fragment.FragmentComponentBuilder
import me.kwsk.odptlibrary.di.fragment.FragmentModule
import me.kwsk.odptlibrary.di.scope.FragmentScope

@Subcomponent(modules = arrayOf(MainFragmentComponent.MainFragmentModule::class))
@FragmentScope
interface MainFragmentComponent : FragmentComponent<MainFragment> {
    @Subcomponent.Builder
    interface Builder : FragmentComponentBuilder<MainFragmentModule, MainFragmentComponent>

    // TODO: Add parameters
    @Module
    class MainFragmentModule internal constructor(fragment: MainFragment) : FragmentModule<MainFragment>(fragment) {
        // NOTE: Add constructor injection here
    }
}
