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
import kozelko.me.oeisandroid.databinding.FragmentResultsBinding

class ResultsFragment:Fragment() {
    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    private val viewModel : SearchViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SearchListAdapter()
        binding.resultsRecycler.adapter = adapter.withLoadStateFooter(SearchListLoadStateFooter())
        binding.resultsRecycler.layoutManager = LinearLayoutManager(context)

        viewModel.sequences.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }

        adapter.addLoadStateListener {
            if (it.refresh is LoadState.NotLoading) {
                binding.resultsRecycler.isVisible = true
            }
        }

        binding.searchField.setText(viewModel.getQuery())
        binding.searchField.setOnEditorActionListener { v, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.search(v.text.trim().toString())
                    true
                }
                else -> false
            }
        }

        binding.btnSearch.setOnClickListener {
            binding.searchField.text.trim().toString().also {
                if (it.isNotEmpty()) {
                    viewModel.search(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
