package kozelko.me.oeisandroid.model

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kozelko.me.oeisandroid.api.OEISApi
import kozelko.me.oeisandroid.api.OEISJson
import kozelko.me.oeisandroid.api.SequenceJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SequencePagingSource(
    private val query : String,
    private val api: OEISApi
) : PagingSource<Int, SequenceJson>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SequenceJson> {
        val start = params.key ?: 0
        Log.d("APP_NETW", "loading with $start")

        try {
            val json = api.search(query, start)
            Log.d("APP_NETW", "Loaded page $start")
            val nextKey = if (start + 10 < json.count) { start + 10 } else { null }
            return LoadResult.Page(json.results!!, null, nextKey)
        } catch (e: IOException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SequenceJson>): Int? {
        return null
    }
}