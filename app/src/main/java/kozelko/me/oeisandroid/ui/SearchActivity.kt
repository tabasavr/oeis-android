package kozelko.me.oeisandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_intro.*
import kozelko.me.oeisandroid.R
import kozelko.me.oeisandroid.api.SequenceJson

class SearchActivity : AppCompatActivity() {
    private lateinit var viewModel : SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, IntroFragment()).commit()
    }
}
