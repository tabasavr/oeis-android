package kozelko.me.oeisandroid.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import kozelko.me.oeisandroid.model.SearchModel

class SearchViewModel : ViewModel() {
    private val model = SearchModel()

    private val currentQuery = MutableLiveData<String>()
    val sequences = switchMap(currentQuery) {
        model.search(it)
    }!!

    fun search(query: String) {
        currentQuery.postValue(query)
    }
}