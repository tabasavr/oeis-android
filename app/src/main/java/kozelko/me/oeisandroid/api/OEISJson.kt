package kozelko.me.oeisandroid.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OEISJson (
    val results : List<SequenceJson>? = null,
    val count : Int = 0,
    val start : Int = 0,
    val query : String? = null
)
