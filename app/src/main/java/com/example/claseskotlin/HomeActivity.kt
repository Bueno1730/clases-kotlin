package com.example.claseskotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.claseskotlin.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dato = intent.extras?.getInt("dato")
        Log.d("HomeActivity", "Dato recibido: $dato")

        binding.textViewTitle.setOnClickListener {
            finish()
        }
    }
}
