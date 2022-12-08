package kozelko.me.oeisandroid.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import kozelko.me.oeisandroid.R
import kozelko.me.oeisandroid.api.SequenceJson

class SearchListAdapter : PagingDataAdapter<SequenceJson, SequenceInfoViewHolder>(SEQUENCE_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SequenceInfoViewHolder {
        val card = LayoutInflater.from(parent.context).inflate(R.layout.sequence_card, parent, false) as CardView
        return SequenceInfoViewHolder(card)
    }

    override fun onBindViewHolder(holder: SequenceInfoViewHolder, position: Int) {
        //if (0 == position) {
        //    holder.setInfo(getItem(position) ?: SequenceJson())
        //} else {
            holder.setSmallInfo(getItem(position) ?: SequenceJson())
        //}
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