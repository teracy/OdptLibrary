package me.kwsk.odptlibrary.di.fragment

import android.support.v4.app.Fragment
import dagger.MembersInjector

interface FragmentComponent<T : Fragment> : MembersInjector<T>
interface FragmentComponentBuilder<M : FragmentModule<*>, C : FragmentComponent<*>> {
    fun getBuilder(fragmentModule: M): FragmentComponentBuilder<M, C>
    fun build(): C
}
