package kozelko.me.oeisandroid

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import androidx.paging.PagedList

class SearchViewModel : ViewModel() {
    val sequences :LiveData<PagedList<SequenceJson>> = MutableLiveData<PagedList<SequenceJson>>()
}