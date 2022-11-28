package kozelko.me.oeisandroid.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import kozelko.me.oeisandroid.model.SearchRepository

class SearchViewModel : ViewModel() {
    private val model = SearchRepository()

    private val currentQuery = MutableLiveData<String>()
    val sequences = switchMap(currentQuery) {
        model.search(it)
    }!!

    fun search(query: String) {
        currentQuery.postValue(query)
    }

    fun getQuery() = currentQuery.value ?: ""
}