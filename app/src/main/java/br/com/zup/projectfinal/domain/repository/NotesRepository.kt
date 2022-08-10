package br.com.zup.projectfinal.domain.repository

import br.com.zup.projectfinal.data.datasource.local.dao.NotesDao
import br.com.zup.projectfinal.data.datasource.local.model.NotesModel

class NotesRepository(private val notesDao: NotesDao) {

    suspend fun insertNote(note: NotesModel){
        notesDao.insertNote(note)
    }

    suspend fun deleteNote(note: String){
        notesDao.deleteNote(note)
    }

    suspend fun getAllNotes(): List<NotesModel> = notesDao.getAllNotes()
}