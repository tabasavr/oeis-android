package kozelko.me.oeisandroid.model

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import java.util.Date
import kozelko.me.oeisandroid.api.OEISApi
import kozelko.me.oeisandroid.api.SequenceJson
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val PAGE_SIZE = 10

class SearchRepository {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(Date::class.java, Rfc3339DateJsonAdapter()).build()
            )
        )
        .baseUrl("https://oeis.org/")
        .build()

    private val api = retrofit.create(OEISApi::class.java)

    fun search(query: String): LiveData<PagingData<SequenceJson>> {
        val sourceFactory = SequencePagingSourceFactory(query, api)
        return Pager(PagingConfig(PAGE_SIZE), null, sourceFactory).liveData
    }
}
