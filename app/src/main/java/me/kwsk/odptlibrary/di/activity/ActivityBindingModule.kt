package me.kwsk.odptlibrary.di.activity

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.kwsk.odptlibrary.view.activity.MainActivity
import me.kwsk.odptlibrary.view.activity.MainActivityComponent

// TODO: Modify @Module annotation accordingly
@Module(subcomponents = arrayOf(MainActivityComponent::class))
abstract class ActivityBindingModule {
    @Binds
    @IntoMap
    @ActivityMapKey(MainActivity::class)
    abstract fun sampleActivityComponentBuilder(builder: MainActivityComponent.Builder): ActivityComponentBuilder<*, *>

    // NOTE: Add injection map here
}
