package kozelko.me.oeisandroid

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class SearchListAdapter : PagedListAdapter<SequenceJson,RecyclerView.ViewHolder>(SEQUENCE_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        val SEQUENCE_COMPARATOR = object : DiffUtil.ItemCallback<SequenceJson>() {
            override fun areItemsTheSame(oldItem: SequenceJson, newItem: SequenceJson): Boolean {
                return oldItem.number == newItem.number
            }

            override fun areContentsTheSame(oldItem: SequenceJson, newItem: SequenceJson): Boolean {
                return oldItem == newItem
            }

        }
    }
}