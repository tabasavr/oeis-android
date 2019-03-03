package kozelko.me.oeisandroid.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.transition.AutoTransition
import kotlinx.android.synthetic.main.fragment_intro.view.*
import kozelko.me.oeisandroid.R

class IntroFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_intro, container, false)
        val viewModel = ViewModelProviders.of(activity!!).get(SearchViewModel::class.java)

        view.btn_search.setOnClickListener {
            view.search_field.text.trim().toString().also {
                if (!it.isEmpty()) {
                    viewModel.search(it)

                    val fragment = ResultsFragment().apply {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            sharedElementEnterTransition = AutoTransition()
                        }
                    }
                    activity!!.supportFragmentManager.beginTransaction()
                        .addSharedElement(view.btn_search, "search_button")
                        .addSharedElement(view.search_field, "search_field")
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                }
            }
        }
        return view
    }
}