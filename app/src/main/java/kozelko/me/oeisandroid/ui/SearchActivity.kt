package kozelko.me.oeisandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kozelko.me.oeisandroid.R

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, IntroFragment()).commit()
    }
}
