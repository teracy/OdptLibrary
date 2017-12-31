package me.kwsk.odptlibrary.di.fragment

import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import me.kwsk.odptlibrary.di.scope.FragmentScope

@Module
abstract class FragmentModule<out T : Fragment>(protected val fragment: T) {
    @Provides
    @FragmentScope
    fun provideFragment(): T {
        return fragment
    }

    // NOTE: Add dependencies here
}
