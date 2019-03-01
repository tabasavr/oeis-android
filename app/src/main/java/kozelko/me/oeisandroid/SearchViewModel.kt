package kozelko.me.oeisandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

class SearchViewModel : ViewModel() {
    val sequences :LiveData<PagedList<SequenceJson>> = MutableLiveData<PagedList<SequenceJson>>()
}