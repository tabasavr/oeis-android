package kozelko.me.oeisandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kozelko.me.oeisandroid.R

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        if (viewModel.getQuery().isEmpty()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, IntroFragment()).commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ResultsFragment()).commit()
        }
    }
}
