package kozelko.me.oeisandroid.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kozelko.me.oeisandroid.model.SearchRepository

private const val QUERY_KEY = "query_key"

class SearchViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val model = SearchRepository()

    val sequences = savedStateHandle.getLiveData<String>(QUERY_KEY).switchMap {
        model.search(it)
    }.cachedIn(viewModelScope)

    fun search(query: String) {
        savedStateHandle[QUERY_KEY] = query
    }

    fun getQuery() = savedStateHandle[QUERY_KEY] ?: ""
}
