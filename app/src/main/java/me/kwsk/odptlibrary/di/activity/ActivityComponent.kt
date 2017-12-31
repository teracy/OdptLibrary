package me.kwsk.odptlibrary.di.activity

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.MembersInjector
import me.kwsk.odptlibrary.di.fragment.FragmentComponentBuilder

interface ActivityComponent<T : AppCompatActivity> : MembersInjector<T> {
    fun getFragmentComponentBuilders(): Map<Class<out Fragment>, FragmentComponentBuilder<*, *>>
}

interface ActivityComponentBuilder<M : ActivityModule<*>, C : ActivityComponent<*>> {
    fun getBuilder(activityModule: M): ActivityComponentBuilder<M, C>
    fun build(): C
}
