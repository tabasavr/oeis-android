package kozelko.me.oeisandroid.api

import retrofit2.http.GET
import retrofit2.http.Query

interface OEISApi {
    @GET("search?fmt=json")
    suspend fun search(@Query("q") query : String, @Query("start") start : Int = 0): OEISJson
}
