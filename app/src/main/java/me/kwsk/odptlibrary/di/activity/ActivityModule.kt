package me.kwsk.odptlibrary.di.activity

import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import me.kwsk.odptlibrary.di.scope.ActivityScope

@Module
abstract class ActivityModule<out T : AppCompatActivity>(protected val activity: T) {
    @Provides
    @ActivityScope
    fun provideActivity(): T {
        return activity
    }

    // NOTE: Add dependencies here
}
