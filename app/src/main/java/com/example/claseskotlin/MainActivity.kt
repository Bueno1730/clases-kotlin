package com.example.claseskotlin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.claseskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button.setOnClickListener {
            val email = binding.editUsername.text.toString()
            val password = binding.edittextPassword.text.toString()
            
            var isValid = true

            if (!email.contains("@")) {
                binding.textInputLayoutUsername.error = "Correo inválido"
                isValid = false
            } else {
                binding.textInputLayoutUsername.error = null
            }

            if (password.length <= 3) {
                binding.textInputLayoutPassword.error = "Password demasiado débil"
                isValid = false
            } else {
                binding.textInputLayoutPassword.error = null
            }
            
            if (isValid) {
                val intent = android.content.Intent(this, HomeActivity::class.java)
                intent.putExtra("dato", 2532)
                startActivity(intent)
            }
        }
    }
}