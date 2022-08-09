package br.com.zup.projectfinal.ui.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.projectfinal.data.datasource.local.model.NotesModel
import br.com.zup.projectfinal.domain.usecase.NotesUseCase
import br.com.zup.projectfinal.ui.viewstate.ViewState
import br.com.zup.projectfinal.utils.DELETE_MSG_NOTE_SUCCESS
import br.com.zup.projectfinal.utils.MSG_NOTE_ERROR
import br.com.zup.projectfinal.utils.MSG_NOTE_SUCCESS
import br.com.zup.projectfinal.utils.NOTE_LIST_ERROR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel(application: Application): AndroidViewModel(application) {
    private val notesUseCase = NotesUseCase(application)

    private var _notesListState = MutableLiveData<ViewState<List<NotesModel>>>()
    val notesListState: LiveData<ViewState<List<NotesModel>>> = _notesListState

    fun insertNote(note: NotesModel){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                notesUseCase.insertNote(note)
            }
        }
    }

    fun deleteNote(note: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                notesUseCase.deleteNote(note)
            }
        }
    }

    fun getAllNotes(){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    notesUseCase.getAllNotes()
                }
                _notesListState.value = response
            }catch (e: Exception){
                _notesListState.value = ViewState.Error(Throwable(NOTE_LIST_ERROR))
            }
        }
    }
}