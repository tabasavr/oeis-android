package kozelko.me.oeisandroid.model

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kozelko.me.oeisandroid.api.OEISApi
import kozelko.me.oeisandroid.api.SequenceJson
import java.io.IOException

class SequencePagingSource(
    private val query : String,
    private val api: OEISApi
) : PagingSource<Int, SequenceJson>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SequenceJson> {
        val start = params.key ?: 0
        Log.d("APP_NETW", "loading with $start")

        try {
            val json = api.search(query, start)
            if (json.results == null) {
                // api sometimes returns null, usually for requests with a lot of results
                //todo: better error handling
                return LoadResult.Error(NullPointerException())
            }

            Log.d("APP_NETW", "Loaded page $start")
            val nextKey = (json.start + json.results.size).takeIf { it < json.count }
            return LoadResult.Page(json.results, null, nextKey)
        } catch (e: IOException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SequenceJson>): Int? {
        return null
    }
}
