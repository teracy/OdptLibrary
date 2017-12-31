package me.kwsk.odptlibrary.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.kwsk.odptlibrary.R
import me.kwsk.odptlibrary.di.activity.FragmentComponentBuilderHost
import javax.inject.Inject


class MainFragment : Fragment() {
    @Inject
    lateinit var sampleText: String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        (activity as FragmentComponentBuilderHost)
                .getFragmentComponentBuilder<MainFragmentComponent.Builder>(this)
                .build()
                .injectMembers(this)

        val view = inflater?.inflate(R.layout.fragment_main, container, false)
        val textView = view?.findViewById<TextView>(R.id.text)
        textView?.setText(sampleText)
        return view
    }

    companion object {
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            fragment.arguments = Bundle()
            return fragment
        }
    }

}// Required empty public constructor
