package me.kwsk.odptlibrary.di.fragment

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.kwsk.odptlibrary.view.fragment.MainFragment
import me.kwsk.odptlibrary.view.fragment.MainFragmentComponent

// TODO: Modify @Module annotation accordingly
@Module(subcomponents = arrayOf(MainFragmentComponent::class))
abstract class FragmentBindingModule {
    @Binds
    @IntoMap
    @FragmentMapKey(MainFragment::class)
    abstract fun sampleFragmentComponentBuilder(builder: MainFragmentComponent.Builder): FragmentComponentBuilder<*, *>

    // NOTE: Add injection map here
}
