package kozelko.me.oeisandroid.model

import android.util.Log
import androidx.paging.PageKeyedDataSource
import kozelko.me.oeisandroid.api.OEISApi
import kozelko.me.oeisandroid.api.OEISJson
import kozelko.me.oeisandroid.api.SequenceJson
import retrofit2.Call
import retrofit2.Response

class SequenceDataSource(
    private val query : String,
    private val api: OEISApi
) : PageKeyedDataSource<Int, SequenceJson>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, SequenceJson>) {
        api.search(query).enqueue(object : retrofit2.Callback<OEISJson> {
            override fun onFailure(call: Call<OEISJson>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<OEISJson>, response: Response<OEISJson>) {
                val json = response.body()!!
                Log.d("APP_NETW", "Loaded initial page " + json.start)
                callback.onResult(json.results!!, 0, json.count, null, if (10 < json.count) 10 else null)
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, SequenceJson>) {
        Log.d("APP_NETW", "loading with " + params.key)
        api.search(query, params.key).enqueue(object : retrofit2.Callback<OEISJson> {
            override fun onFailure(call: Call<OEISJson>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<OEISJson>, response: Response<OEISJson>) {
                val json = response.body()!!
                Log.d("APP_NETW", "Loaded page " + json.start + " with " + params.key)
                callback.onResult(json.results!!, if (params.key + 10 < json.count) params.key + 10 else null)
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, SequenceJson>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}