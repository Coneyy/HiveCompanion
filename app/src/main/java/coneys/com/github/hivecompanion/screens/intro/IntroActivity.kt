package coneys.com.github.hivecompanion.screens.intro

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import coneys.com.github.hivecompanion.App
import coneys.com.github.hivecompanion.R
import coneys.com.github.hivecompanion.repositories.UserRepository
import coneys.com.github.hivecompanion.screens.matchHistory.view.HistoryActivity
import coneys.com.github.hivecompanion.screens.scoreTable.ScoreActivity
import coneys.com.github.hivecompanion.screens.start.login.LoginActivity
import kotlinx.android.synthetic.main.activity_intro.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class IntroActivity : AppCompatActivity() {

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        App.injector.appComponent.inject(this)
        match_history.onClick { startActivity(Intent(this, HistoryActivity::class.java)) }
        high_score.onClick { startActivity(Intent(this, ScoreActivity::class.java)) }
        log_out_button.onClick { logOut(); }

        swipe_btn.setOnActiveListener {
            userRepository.getCurrentUser()?.let {
                startHive(it.username, it.email)
            }
        }


    }

    private fun logOut() {
        userRepository.clearUser()
        finishAffinity()
        startActivity<LoginActivity>()
    }

    private fun startHive(username: String, email: String) {
        val launchIntentForPackage: Intent = packageManager.
                getLaunchIntentForPackage("com.TR.Hive")
        launchIntentForPackage.action = Intent.ACTION_MAIN
        val extras = Bundle()
        extras.putString("USERNAME", username)
        extras.putString("EMAIL", email)
        launchIntentForPackage.putExtras(extras)
        startActivity(launchIntentForPackage)
    }
}
