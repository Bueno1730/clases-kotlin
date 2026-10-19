package com.example.claseskotlin.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.claseskotlin.data.NotesDatabase
import com.example.claseskotlin.databinding.ActivityNotesBinding
import com.example.claseskotlin.domain.models.NoteModel

class NotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding
    private lateinit var adapter: NotesAdapter

    private val formResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra("NOTE_KEY", NoteModel::class.java)
            } else {
                @Suppress("DEPRECATION")
                result.data?.getParcelableExtra<NoteModel>("NOTE_KEY")
            }

            if (note != null) {
                note.id = adapter.itemCount + 1
                adapter.insertNoteModel(note)
                binding.llMessage.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = NotesAdapter(NotesDatabase.getFakeNotes().toMutableList())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        if (adapter.itemCount == 0) {
            binding.recyclerView.visibility = View.GONE
            binding.llMessage.visibility = View.VISIBLE
        } else {
            binding.recyclerView.visibility = View.VISIBLE
            binding.llMessage.visibility = View.GONE
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, FormNoteActivity::class.java)
            formResultLauncher.launch(intent)
        }
    }
}
