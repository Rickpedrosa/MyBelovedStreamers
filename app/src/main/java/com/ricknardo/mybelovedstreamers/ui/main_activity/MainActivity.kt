package com.ricknardo.mybelovedstreamers.ui.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.ricknardo.mybelovedstreamers.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController: NavController = Navigation.findNavController(this, R.id.navHostFragment)
//        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            AppBarConfiguration.Builder(navController.graph).build()
        )
    }
}
