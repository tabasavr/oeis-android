package kozelko.me.oeisandroid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class SearchActivity : AppCompatActivity() {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(Date::class.java, Rfc3339DateJsonAdapter()).build()))
        .baseUrl("https://oeis.org/")
        .build()

    val apiCallback = object :Callback<OEISJson> {
        override fun onResponse(call: Call<OEISJson>, response: Response<OEISJson>) {
            Log.d("APP", "Got response")
            tmp_info.setInfo(response.body()!!.results!![0])
        }

        override fun onFailure(call: Call<OEISJson>, t: Throwable) {
            Log.d("APP", "Response failed")
            Log.d("APP", t.message)
        }
    }

    var call : Call<OEISJson>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        results_list.adapter = SearchListAdapter()


        val api = retrofit.create(OEISApi::class.java)

        call = api.search("1,2,3,4,5")
        call?.enqueue(apiCallback)

        tmp_info.setInfo(SequenceJson())
    }

    override fun onDestroy() {
        call?.cancel();
        super.onDestroy()
    }
}
