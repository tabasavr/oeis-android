package kozelko.me.oeisandroid

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class SequenceInfoViewHolder(view : CardView) : RecyclerView.ViewHolder(view) {
    private val info = view.findViewById<SequenceInfo>(R.id.info)!!

    fun setInfo(json : SequenceJson) {
        info.setInfo(json)
    }

    fun setSmallInfo(json: SequenceJson) {
        info.setSmallInfo(json)
    }
}