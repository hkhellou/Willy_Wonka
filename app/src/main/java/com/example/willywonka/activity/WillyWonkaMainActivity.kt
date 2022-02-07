package com.example.willywonka.activity

import android.app.StatusBarManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.willywonka.R
import com.example.willywonka.databinding.ActivityMainBinding
import com.example.willywonka.fragments.OompaLoompaWorkersFragment

class WillyWonkaMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initListeners()
    }

    private fun initValues() {
        setSupportActionBar(binding.toolbar)
    }

    private fun initListeners() {
        binding.toolbar.setNavigationOnClickListener{
            onBackPressed()
        }
    }
}