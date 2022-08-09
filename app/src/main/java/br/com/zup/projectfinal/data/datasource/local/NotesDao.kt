package br.com.zup.projectfinal.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): List<NotesModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(notesModel: NotesModel)

    @Query("DELETE FROM notes_table WHERE note = :notesModel")
    fun deleteNote(notesModel: NotesModel)
}