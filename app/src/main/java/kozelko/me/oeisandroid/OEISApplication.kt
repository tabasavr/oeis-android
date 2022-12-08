package kozelko.me.oeisandroid

import android.app.Application
import com.google.android.material.color.DynamicColors

class OEISApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}
