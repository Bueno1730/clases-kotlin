package com.example.claseskotlin.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.claseskotlin.databinding.ItemNoteBinding
import com.example.claseskotlin.domain.models.NoteModel

class NotesAdapter(private val items: MutableList<NoteModel>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = items[position]
        holder.binding.textViewTitle.text = note.title
        holder.binding.textViewContent.text = note.content
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun insertNoteModel(note: NoteModel) {
        items.add(note)
        notifyDataSetChanged()
    }
}
