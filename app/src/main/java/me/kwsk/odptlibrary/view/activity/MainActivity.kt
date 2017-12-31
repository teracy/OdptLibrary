package me.kwsk.odptlibrary.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import me.kwsk.odptlibrary.R
import me.kwsk.odptlibrary.di.DaggerUtil
import me.kwsk.odptlibrary.di.activity.FragmentComponentBuilderHost
import me.kwsk.odptlibrary.di.fragment.FragmentComponentBuilder
import me.kwsk.odptlibrary.view.fragment.MainFragment
import me.kwsk.odptlibrary.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), FragmentComponentBuilderHost {
    private var activityComponent: MainActivityComponent? = null
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent = DaggerUtil.getComponentBuilder<MainActivityComponent.Builder>(this)
                .getBuilder(MainActivityComponent.MainActivityModule(this))
                .build()
        activityComponent?.injectMembers(this)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        if (viewModel != null) {
            // TODO: Add implementation
        }

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, MainFragment.newInstance())
        transaction.commit()

        // TEST
        viewModel?.getData()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : FragmentComponentBuilder<*, *>> getFragmentComponentBuilder(fragment: Fragment): T {
        return activityComponent?.getFragmentComponentBuilders()?.get(fragment::class.java) as T
    }
}
