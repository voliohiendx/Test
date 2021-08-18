package volio.tech.sharefile

import android.app.Application
import android.graphics.Color
import androidx.appcompat.app.AppCompatDelegate
import com.zxy.recovery.core.Recovery
import dagger.hilt.android.HiltAndroidApp
import jp.wasabeef.takt.Seat
import jp.wasabeef.takt.Takt
import volio.tech.sharefile.framework.MainActivity

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //TODO: enable/disable dark theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        Recovery.getInstance()
            .debug(true)
            .recoverInBackground(false)
            .recoverStack(true)
            .mainPage(MainActivity::class.java)
            .recoverEnabled(true)
            .silent(false, Recovery.SilentMode.RECOVER_ACTIVITY_STACK)
            .init(this)

        Takt.stock(this)
            .seat(Seat.TOP_CENTER)
            .interval(250)
            .color(Color.RED)
            .size(14f)
            .alpha(.5f)
    }

}
