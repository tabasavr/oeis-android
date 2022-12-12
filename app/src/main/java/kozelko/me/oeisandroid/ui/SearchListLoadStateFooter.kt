package kozelko.me.oeisandroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import kozelko.me.oeisandroid.R

// todo: support error
class SearchListLoadStateFooter : LoadStateAdapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, loadState: LoadState) {
        // no-op
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FooterViewHolder(
            inflater.inflate(R.layout.sequence_load_state_footer, parent, false)
        )
    }
}

private class FooterViewHolder(item: View) : RecyclerView.ViewHolder(item)
