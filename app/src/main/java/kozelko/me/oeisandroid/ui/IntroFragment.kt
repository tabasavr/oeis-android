package kozelko.me.oeisandroid.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.transition.AutoTransition
import kotlinx.android.synthetic.main.fragment_intro.view.*
import kozelko.me.oeisandroid.R

class IntroFragment: Fragment() {
    private val viewModel by activityViewModels<SearchViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_intro, container, false)

        view.search_field.setOnEditorActionListener { v, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    doSearch(v.text.trim().toString())
                    true
                }
                else -> false
            }
        }

        view.btn_search.setOnClickListener {
            view.search_field.text.trim().toString().also {
                doSearch(it)
            }
        }
        return view
    }

    private fun doSearch(query: String) {
        if (query.isNotBlank()) {
            viewModel.search(query)

            view?.let {
                val fragment = ResultsFragment().apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        sharedElementEnterTransition = AutoTransition()
                    }
                }
                requireActivity().supportFragmentManager.beginTransaction()
                    .addSharedElement(it.btn_search, "search_button")
                    .addSharedElement(it.search_field, "search_field")
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            }
        }
    }
}