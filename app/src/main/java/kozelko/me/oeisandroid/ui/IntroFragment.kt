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
import kozelko.me.oeisandroid.R
import kozelko.me.oeisandroid.databinding.FragmentIntroBinding

class IntroFragment: Fragment() {
    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<SearchViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchField.setOnEditorActionListener { v, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    doSearch(v.text.trim().toString())
                    true
                }
                else -> false
            }
        }

        binding.btnSearch.setOnClickListener {
            binding.searchField.text.trim().toString().also {
                doSearch(it)
            }
        }
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
                    .addSharedElement(binding.btnSearch, "search_button")
                    .addSharedElement(binding.searchField, "search_field")
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
