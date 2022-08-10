package br.com.zup.projectfinal.domain.usecase

import android.app.Application
import br.com.zup.projectfinal.data.datasource.local.NotesDatabase
import br.com.zup.projectfinal.data.datasource.local.model.NotesModel
import br.com.zup.projectfinal.domain.repository.NotesRepository
import br.com.zup.projectfinal.ui.viewstate.ViewState
import br.com.zup.projectfinal.utils.NOTE_LIST_ERROR

class NotesUseCase(application: Application) {
    private val notesDao = NotesDatabase.getCharacterDatabase(application).notesDao()
    private val notesRepository = NotesRepository(notesDao)

   suspend fun insertNote(note: NotesModel){
        notesRepository.insertNote(note)
    }

   suspend fun deleteNote(note: String){
        notesRepository.deleteNote(note)
    }

   suspend fun getAllNotes(): ViewState<List<NotesModel>>{
       return try {
           val notes = notesRepository.getAllNotes()
           ViewState.Success(notes)
       } catch (ex: Exception) {
           ViewState.Error(Exception(NOTE_LIST_ERROR))
       }
    }
}