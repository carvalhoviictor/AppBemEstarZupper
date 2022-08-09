package br.com.zup.projectfinal.domain.repository

import br.com.zup.projectfinal.data.datasource.local.dao.NotesDao
import br.com.zup.projectfinal.data.datasource.local.model.NotesModel

class NotesRepository(private val notesDao: NotesDao) {

    fun insertNote(note: NotesModel){
        notesDao.insertNote(note)
    }

    fun deleteNote(note: NotesModel){
        notesDao.deleteNote(note)
    }

    fun getAllNotes(): List<NotesModel> = notesDao.getAllNotes()
}