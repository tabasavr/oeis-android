package kozelko.me.oeisandroid

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OEISApi {
    @GET("search?fmt=json")
    fun search(@Query("q") query : String, @Query("start") start : Int = 0) : Call<OEISJson>
}