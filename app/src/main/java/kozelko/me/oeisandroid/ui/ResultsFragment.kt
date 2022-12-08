package kozelko.me.oeisandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_results.view.*
import kotlinx.android.synthetic.main.fragment_results.view.btn_search
import kotlinx.android.synthetic.main.fragment_results.view.search_field
import kozelko.me.oeisandroid.R

class ResultsFragment:Fragment() {
    private val viewModel : SearchViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_results, container, false)

        val adapter = SearchListAdapter()
        view.results_list.adapter = adapter.withLoadStateFooter(SearchListLoadStateFooter())
        view.results_list.layoutManager = LinearLayoutManager(context)

        viewModel.sequences.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }

        adapter.addLoadStateListener {
            if (it.refresh is LoadState.NotLoading) {
                view.results_list.isVisible = true
            }
        }

        view.search_field.setText(viewModel.getQuery())
        view.search_field.setOnEditorActionListener { v, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.search(v.text.trim().toString())
                    true
                }
                else -> false
            }
        }

        view.btn_search.setOnClickListener {
            view.search_field.text.trim().toString().also {
                if (it.isNotEmpty()) {
                    viewModel.search(it)
                }
            }
        }
        return view
    }
}