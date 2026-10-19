package com.example.claseskotlin.data

import com.example.claseskotlin.domain.models.NoteModel

object NotesDatabase {
    fun getFakeNotes(): List<NoteModel> {
        return listOf(
            NoteModel(
                id = 1,
                title = "Comprar víveres",
                content = "Comprar leche, huevos, pan y café",
                createdAt = "2026-10-12"
            ),
            NoteModel(
                id = 2,
                title = "Reunión de trabajo",
                content = "Presentación del proyecto Clean Architecture",
                createdAt = "2026-10-12"
            ),
            NoteModel(
                id = 3,
                title = "Estudiar Kotlin",
                content = "Repasar View Binding, capas Domain y Data",
                createdAt = "2026-10-13"
            ),
            NoteModel(
                id = 4,
                title = "Hacer ejercicio",
                content = "Rutina de cardio y pesas 45 minutos",
                createdAt = "2026-10-13"
            ),
            NoteModel(
                id = 5,
                title = "Leer libro",
                content = "Leer 20 páginas de Clean Architecture",
                createdAt = "2026-10-14"
            )
        )
    }
}
