package swing.dev.cats.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import swing.dev.cats.MainActivity
import swing.dev.cats.extensions.FADE_IN_ACTIVITY
import swing.dev.cats.extensions.navigateToActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToActivity(MainActivity::class.java, null, FADE_IN_ACTIVITY)
        finish()
    }
}