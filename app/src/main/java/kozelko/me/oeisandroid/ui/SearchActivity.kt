package kozelko.me.oeisandroid.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import kozelko.me.oeisandroid.R

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(findViewById(R.id.toolbar))
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val viewModel by viewModels<SearchViewModel>()

        if (viewModel.getQuery().isEmpty()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, IntroFragment()).commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ResultsFragment()).commit()
        }
    }
}
