package br.com.zup.projectfinal.ui.notes.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.projectfinal.R
import br.com.zup.projectfinal.data.datasource.local.model.NotesModel
import br.com.zup.projectfinal.databinding.FragmentNotesBinding
import br.com.zup.projectfinal.initial.InitialActivity
import br.com.zup.projectfinal.ui.notes.view.adapter.NotesAdapter
import br.com.zup.projectfinal.ui.notes.viewmodel.NotesViewModel
import br.com.zup.projectfinal.ui.viewstate.ViewState
import br.com.zup.projectfinal.utils.DELETE_MSG_NOTE_SUCCESS
import br.com.zup.projectfinal.utils.MSG_NOTE_SUCCESS
import br.com.zup.projectfinal.utils.REQUIRED_FIELD
import br.com.zup.projectfinal.utils.TITLE_NOTES

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding

    private val viewModel: NotesViewModel by lazy {
        ViewModelProvider(this)[NotesViewModel::class.java]
    }

    private val notesAdapter: NotesAdapter by lazy {
        NotesAdapter(arrayListOf(), this::deleteNote)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        actionBarAccess()

        viewModel.getAllNotes()
        initObserver()
        showRecyclerView()
        saveButton()
    }

    private fun saveButton() {
        binding.btnSaveNote.setOnClickListener {
            hideKeyboard()
            saveNote()
            viewModel.getAllNotes()
        }
    }

    private fun actionBarAccess() {
        (activity as InitialActivity).supportActionBar?.show()
        (activity as InitialActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as InitialActivity).supportActionBar?.title = TITLE_NOTES
    }

    private fun showRecyclerView() {
        binding.rvNotes.adapter = notesAdapter
        binding.rvNotes.layoutManager = LinearLayoutManager(context)
    }

    private fun saveNote() {
        if (validateField()) {
            viewModel.insertNote(getNote())
            hideKeyboard()
            Toast.makeText(context, MSG_NOTE_SUCCESS, Toast.LENGTH_LONG).show()
            clearField()
        }
    }

    private fun getNote(): NotesModel {
        val note = binding.etNotesReminders.text.toString()
        return NotesModel(note = note)
    }

    private fun validateField(): Boolean {
        return if (binding.etNotesReminders.text.isEmpty()) {
            binding.etNotesReminders.error = REQUIRED_FIELD
            false
        } else {
            true
        }
    }

    private fun clearField() {
        binding.etNotesReminders.text.clear()
    }

    private fun deleteNote(note: NotesModel) {
        viewModel.deleteNote(note.note)
        hideKeyboard()
        Toast.makeText(context, DELETE_MSG_NOTE_SUCCESS, Toast.LENGTH_LONG).show()
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun initObserver() {

        viewModel.notesListState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    notesAdapter.updateNotesList(it.data.toMutableList())
                }
                is ViewState.Error -> {
                    hideKeyboard()
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun navigateToProfileFragment() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_notesFragment_to_profileFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                navigateToProfileFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}