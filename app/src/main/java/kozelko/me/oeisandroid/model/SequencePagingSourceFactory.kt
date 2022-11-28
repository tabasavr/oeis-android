package kozelko.me.oeisandroid.model

import androidx.paging.PagingSource
import kozelko.me.oeisandroid.api.OEISApi
import kozelko.me.oeisandroid.api.SequenceJson

class SequencePagingSourceFactory(
    private val query: String,
    private val api: OEISApi,
): () -> PagingSource<Int, SequenceJson> {
    override fun invoke(): PagingSource<Int, SequenceJson> {
        return SequencePagingSource(query, api)
    }
}