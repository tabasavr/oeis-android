package kozelko.me.oeisandroid

import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

class SearchListAdapter : PagedListAdapter<SequenceJson, SequenceInfoViewHolder>(SEQUENCE_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SequenceInfoViewHolder {
        val card = LayoutInflater.from(parent.context).inflate(R.layout.sequence_card, parent, false) as CardView
        card.cardElevation = 5f
        card.layoutParams = ViewGroup.MarginLayoutParams(card.layoutParams).apply { setMargins(8,8,8,8) }
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