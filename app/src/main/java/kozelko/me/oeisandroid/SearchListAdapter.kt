package kozelko.me.oeisandroid

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

class SearchListAdapter : PagedListAdapter<SequenceJson, SequenceInfoViewHolder>(SEQUENCE_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SequenceInfoViewHolder {
        return SequenceInfoViewHolder(SequenceInfo(parent.context))
    }

    override fun onBindViewHolder(holder: SequenceInfoViewHolder, position: Int) {
        holder.setInfo(getItem(position)!!)
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