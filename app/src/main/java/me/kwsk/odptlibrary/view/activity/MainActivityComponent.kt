package me.kwsk.odptlibrary.view.activity

import dagger.Module
import dagger.Subcomponent
import me.kwsk.odptlibrary.di.activity.ActivityComponent
import me.kwsk.odptlibrary.di.activity.ActivityComponentBuilder
import me.kwsk.odptlibrary.di.activity.ActivityModule
import me.kwsk.odptlibrary.di.fragment.FragmentBindingModule
import me.kwsk.odptlibrary.di.scope.ActivityScope

@Subcomponent(modules = arrayOf(MainActivityComponent.MainActivityModule::class, FragmentBindingModule::class))
@ActivityScope
interface MainActivityComponent : ActivityComponent<MainActivity> {
    @Subcomponent.Builder
    interface Builder : ActivityComponentBuilder<MainActivityModule, MainActivityComponent>

    // TODO: Add parameters
    @Module
    class MainActivityModule internal constructor(activity: MainActivity) : ActivityModule<MainActivity>(activity) {
        // NOTE: Add constructor injection here
    }
}
