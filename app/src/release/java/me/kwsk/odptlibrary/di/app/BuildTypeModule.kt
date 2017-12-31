package me.kwsk.odptlibrary.di.app

import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class BuildTypeModule {
    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor.Level {
        return HttpLoggingInterceptor.Level.NONE
    }
}