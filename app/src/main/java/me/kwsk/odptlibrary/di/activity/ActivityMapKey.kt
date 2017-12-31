package me.kwsk.odptlibrary.di.activity

import android.support.v7.app.AppCompatActivity
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class ActivityMapKey(val value: KClass<out AppCompatActivity>)
