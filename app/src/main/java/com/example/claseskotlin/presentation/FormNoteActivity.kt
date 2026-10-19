package com.example.claseskotlin.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.claseskotlin.databinding.ActivityFormNoteBinding
import com.example.claseskotlin.domain.models.NoteModel

class FormNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFormNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUI(intent.extras != null)

        binding.btnSaveNote.setOnClickListener {
            if (isValidForm()) {
                val title = binding.editTextTitle.text.toString()
                val content = binding.editTextContent.text.toString()
                val noteModel = NoteModel(id = null, title = title, content = content, createdAt = "Hoy")
                val resultIntent = Intent()
                resultIntent.putExtra("NOTE_KEY", noteModel)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.iconBack.setOnClickListener {
            finish()
        }
    }

    private fun isValidForm(): Boolean {
        var isValid = true

        if (binding.editTextTitle.text.toString().isEmpty()) {
            binding.textInputLayoutTitle.error = "campo requerido"
            isValid = false
        } else {
            binding.textInputLayoutTitle.error = null
        }

        if (binding.editTextContent.text.toString().isEmpty()) {
            binding.textInputLayoutContent.error = "campo requerido"
            isValid = false
        } else {
            binding.textInputLayoutContent.error = null
        }

        return isValid
    }

    private fun setupUI(isUpdate: Boolean) {
        if (isUpdate) {
            binding.llUpdateButtons.visibility = View.VISIBLE
            binding.btnSaveNote.visibility = View.GONE
            binding.textViewTitle.text = "Actualización"
        } else {
            binding.llUpdateButtons.visibility = View.GONE
            binding.btnSaveNote.visibility = View.VISIBLE
            binding.textViewTitle.text = "Registro"
        }
    }
}
