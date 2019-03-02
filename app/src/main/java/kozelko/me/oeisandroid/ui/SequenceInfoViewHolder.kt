package kozelko.me.oeisandroid.ui

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kozelko.me.oeisandroid.R
import kozelko.me.oeisandroid.api.SequenceJson

class SequenceInfoViewHolder(view : CardView) : RecyclerView.ViewHolder(view) {
    private val info = view.findViewById<SequenceInfoView>(R.id.info)!!

    fun setInfo(json : SequenceJson) {
        info.setInfo(json)
    }

    fun setSmallInfo(json: SequenceJson) {
        info.setSmallInfo(json)
    }
}