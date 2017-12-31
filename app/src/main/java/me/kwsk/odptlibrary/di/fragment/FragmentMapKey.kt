package me.kwsk.odptlibrary.di.fragment

import android.support.v4.app.Fragment
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class FragmentMapKey(val value: KClass<out Fragment>)
