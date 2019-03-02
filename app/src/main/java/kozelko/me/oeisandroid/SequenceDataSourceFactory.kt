package kozelko.me.oeisandroid

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class SequenceDataSourceFactory(private val query: String, private val api: OEISApi) : DataSource.Factory<Int, SequenceJson>() {
    val sourceLiveData = MutableLiveData<SequenceDataSource>()
    var latestSource : SequenceDataSource? = null
    override fun create(): DataSource<Int, SequenceJson> {
        latestSource = SequenceDataSource(query, api)
        sourceLiveData.postValue(latestSource)
        return latestSource!!
    }
}