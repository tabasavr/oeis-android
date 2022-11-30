package kozelko.me.oeisandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_results.view.*
import kozelko.me.oeisandroid.R

class ResultsFragment:Fragment() {
    private lateinit var viewModel : SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_results, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        view.results_list.adapter = SearchListAdapter()
        view.results_list.layoutManager = LinearLayoutManager(context)

        viewModel.sequences.observe(viewLifecycleOwner) {
            view.results_list.visibility = RecyclerView.VISIBLE
            (view.results_list.adapter as SearchListAdapter).submitList(it)
        }

        view.search_field.setText(viewModel.getQuery())

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