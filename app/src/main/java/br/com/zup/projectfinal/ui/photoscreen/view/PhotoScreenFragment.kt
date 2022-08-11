package br.com.zup.projectfinal.ui.photoscreen.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.projectfinal.R
import br.com.zup.projectfinal.databinding.FragmentPhotoScreenBinding
import br.com.zup.projectfinal.ui.notes.viewmodel.NotesViewModel
import br.com.zup.projectfinal.ui.photoscreen.viewmodel.PhotoScreenViewModel

import java.text.SimpleDateFormat
import java.util.*

class PhotoScreenFragment : Fragment() {
    private lateinit var binding: FragmentPhotoScreenBinding

    private val viewModel: PhotoScreenViewModel by lazy {
        ViewModelProvider(this)[PhotoScreenViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showCurrentDateText()
    }

    private fun showCurrentDateText(){
        var date = Calendar.getInstance().time

        var dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        var textDate = buildString {
            append("Foto Motivacional do Dia | ")
            append(dateTimeFormat.format(date))
        }
        binding.tvDate.text = textDate
    }

    private fun navigateToLoginFragment() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_notesFragment_to_loginFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.exit -> {
                viewModel.logout()
                this.onDestroy()
                navigateToLoginFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}