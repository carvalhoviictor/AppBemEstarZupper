package br.com.zup.projectfinal.domain.usecase

import android.app.Application
import br.com.zup.projectfinal.data.datasource.local.NotesDatabase
import br.com.zup.projectfinal.data.datasource.local.model.NotesModel
import br.com.zup.projectfinal.domain.repository.NotesRepository

class NotesUseCase(application: Application) {
    private val notesDao = NotesDatabase.getCharacterDatabase(application).notesDao()
    private val notesRepository = NotesRepository(notesDao)

    fun insertNote(note: NotesModel){
        notesRepository.insertNote(note)
    }

    fun deleteNote(note: NotesModel){
        notesRepository.deleteNote(note)
    }

    fun getAllNotes(): List<NotesModel>{
        return notesRepository.getAllNotes()
    }
}