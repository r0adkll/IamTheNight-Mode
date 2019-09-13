package com.r0adkll.batapp

import android.content.res.Configuration
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var lastUiMode: Int = Configuration.UI_MODE_NIGHT_UNDEFINED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lastUiMode = savedInstanceState?.getInt("uiMode", Configuration.UI_MODE_NIGHT_UNDEFINED)
            ?: Configuration.UI_MODE_NIGHT_UNDEFINED

        setContentView(R.layout.activity_main)
        setSupportActionBar(appBar)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val nightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (nightMode != lastUiMode && lastUiMode != Configuration.UI_MODE_NIGHT_UNDEFINED) {
            findNavController(R.id.nav_host_fragment).apply {
                popBackStack()
                navigate(currentDestination?.id ?: R.id.navigation_home)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("uiMode", resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK)
    }
}
