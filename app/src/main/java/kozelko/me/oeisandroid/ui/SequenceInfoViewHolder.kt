package kozelko.me.oeisandroid.ui

import androidx.recyclerview.widget.RecyclerView
import kozelko.me.oeisandroid.api.SequenceJson
import kozelko.me.oeisandroid.databinding.SequenceCardBinding

class SequenceInfoViewHolder(private val binding: SequenceCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val info
        get() = binding.info

    fun setInfo(json : SequenceJson) {
        info.setInfo(json)
    }

    fun setSmallInfo(json: SequenceJson) {
        info.setSmallInfo(json)
    }
}