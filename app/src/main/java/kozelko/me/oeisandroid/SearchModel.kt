package kozelko.me.oeisandroid

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class SearchModel {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(Date::class.java, Rfc3339DateJsonAdapter()).build()))
        .baseUrl("https://oeis.org/")
        .build()

    private val api = retrofit.create(OEISApi::class.java)

    fun search(query:String) : LiveData<PagedList<SequenceJson>> {
        val sourceFactory = SequenceDataSourceFactory(query, api)
        return sourceFactory.toLiveData(10)
    }
}