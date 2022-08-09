package br.com.zup.projectfinal.ui.notes.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.projectfinal.data.datasource.local.model.NotesModel
import br.com.zup.projectfinal.databinding.NoteItemBinding

class NotesAdapter (
    private var notesList: MutableList<NotesModel>,
    private val clickDelete: (notesModel: NotesModel) -> Unit
): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    class ViewHolder(val binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root){
        fun showMessage(notesModel: NotesModel){
            binding.tvNoteReminder.text = notesModel.note
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val savedNote = notesList[position]
        holder.showMessage(savedNote)

        holder.binding.ivDelete.setOnClickListener {
            clickDelete(savedNote)
            notesList.remove(savedNote)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun updateNotesList(newList: MutableList<NotesModel>){
        if (notesList.size == 0){
            notesList = newList
        }else{
            notesList = mutableListOf()
            notesList.addAll(newList)
        }
        notifyDataSetChanged()
    }
}