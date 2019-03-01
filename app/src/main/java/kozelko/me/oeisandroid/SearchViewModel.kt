package kozelko.me.oeisandroid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    private val model = SearchModel()

    private val currentQuery = MutableLiveData<String>()
    val sequences = switchMap(currentQuery) {
        model.search(it)
    }!!

    public fun search(query: String) {
        currentQuery.postValue(query)
    }
}