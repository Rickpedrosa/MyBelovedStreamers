package com.ricknardo.mybelovedstreamers.ui.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

        val viewModel: MainActivityViewModel = ViewModelProvider(
            this,
            MainActivityViewModelFactory(application)
        ).get(MainActivityViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.storeTwitchToken()
        }

        viewModel.applicateToastMessagerLiveData.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        })

        val navController: NavController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            AppBarConfiguration.Builder(navController.graph).build()
        )
    }
}
