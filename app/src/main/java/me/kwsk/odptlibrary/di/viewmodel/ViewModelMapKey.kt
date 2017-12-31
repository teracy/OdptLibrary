package me.kwsk.odptlibrary.di.viewmodel

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class ViewModelMapKey(val value: KClass<out ViewModel>)
