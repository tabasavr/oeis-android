package kozelko.me.oeisandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    private lateinit var viewModel : SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        results_list.adapter = SearchListAdapter()

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewModel.sequences.observe(this, Observer<PagedList<SequenceJson>> {
            (results_list.adapter as SearchListAdapter).submitList(it)
        })


        viewModel.search("1,2,3,4,5")
    }
}
