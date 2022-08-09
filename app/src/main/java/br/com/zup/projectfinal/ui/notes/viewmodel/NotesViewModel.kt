package br.com.zup.projectfinal.ui.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.projectfinal.data.datasource.local.model.NotesModel
import br.com.zup.projectfinal.domain.usecase.NotesUseCase
import br.com.zup.projectfinal.utils.DELETE_MSG_NOTE_SUCCESS
import br.com.zup.projectfinal.utils.MSG_NOTE_ERROR
import br.com.zup.projectfinal.utils.MSG_NOTE_SUCCESS

class NotesViewModel(application: Application): AndroidViewModel(application) {
    private val notesUseCase = NotesUseCase(application)

    private var _notesListState = MutableLiveData<List<NotesModel>>()
    val notesListState: LiveData<List<NotesModel>> = _notesListState

    private var _stateMsg = MutableLiveData<String>()
    val stateMsg: LiveData<String> = _stateMsg

    fun insertNote(note: NotesModel){
        try {
            notesUseCase.insertNote(note)
            _stateMsg.value = MSG_NOTE_SUCCESS
        }catch(e: Exception){
            _stateMsg.value = MSG_NOTE_ERROR
        }
    }

    fun deleteNote(note: NotesModel){
        try {
            notesUseCase.deleteNote(note)
            _stateMsg.value = DELETE_MSG_NOTE_SUCCESS
        }catch (ex: Exception){
            _stateMsg.value = MSG_NOTE_ERROR
        }
    }

    fun getAllNotes(){
        try {
            _notesListState.value = notesUseCase.getAllNotes()
        }catch (ex: Exception){
            _notesListState.value = arrayListOf()
            _stateMsg.value = MSG_NOTE_SUCCESS
        }
    }
}