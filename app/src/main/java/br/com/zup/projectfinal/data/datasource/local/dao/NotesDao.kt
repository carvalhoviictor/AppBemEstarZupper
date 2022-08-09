package br.com.zup.projectfinal.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.projectfinal.data.datasource.local.model.NotesModel

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): List<NotesModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(notesModel: NotesModel)

    @Query("DELETE FROM notes_table WHERE note = :selectedNote")
    fun deleteNote(selectedNote: String)
}