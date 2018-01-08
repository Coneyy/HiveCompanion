package coneys.com.github.hivecompanion.base.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import coneys.com.github.hivecompanion.R

abstract class ContainerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container_activity)

    }
}