package me.kwsk.odptlibrary.di.activity

import android.support.v4.app.Fragment
import me.kwsk.odptlibrary.di.fragment.FragmentComponentBuilder

interface FragmentComponentBuilderHost {
    fun <T : FragmentComponentBuilder<*, *>> getFragmentComponentBuilder(fragment: Fragment): T;
}
