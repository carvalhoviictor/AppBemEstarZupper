package br.com.zup.projectfinal.data.datasource.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
data class NotesModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_note")
    var id: Long = 0,

    @ColumnInfo(name = "note")
    var note: String

): Parcelable
