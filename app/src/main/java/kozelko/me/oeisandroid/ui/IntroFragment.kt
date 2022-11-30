package kozelko.me.oeisandroid.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.AutoTransition
import kotlinx.android.synthetic.main.fragment_intro.view.*
import kozelko.me.oeisandroid.R

class IntroFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_intro, container, false)
        val viewModel by viewModels<SearchViewModel>()

        view.btn_search.setOnClickListener {
            view.search_field.text.trim().toString().also {
                if (it.isNotEmpty()) {
                    viewModel.search(it)

                    val fragment = ResultsFragment().apply {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            sharedElementEnterTransition = AutoTransition()
                        }
                    }
                    requireActivity().supportFragmentManager.beginTransaction()
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