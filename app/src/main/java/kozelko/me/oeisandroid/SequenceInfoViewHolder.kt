package kozelko.me.oeisandroid

import androidx.recyclerview.widget.RecyclerView

class SequenceInfoViewHolder(private val view : SequenceInfo) : RecyclerView.ViewHolder(view) {
    public fun setInfo(json : SequenceJson) {
        view.setInfo(json)
    }
}