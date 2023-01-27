package com.example.artyomkafood.app

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.artyomkafood.R
import com.example.artyomkafood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navController) as NavHostFragment

        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
        setContentView(binding.root)
    }
}