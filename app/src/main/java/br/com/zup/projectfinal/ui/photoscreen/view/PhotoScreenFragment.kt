package br.com.zup.projectfinal.ui.photoscreen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.projectfinal.databinding.FragmentPhotoScreenBinding
import br.com.zup.projectfinal.domain.model.Image
import br.com.zup.projectfinal.ui.ViewState
import br.com.zup.projectfinal.ui.photoscreen.viewmodel.PhotoScreenViewModel
import com.squareup.picasso.Picasso

import java.text.SimpleDateFormat
import java.util.*

class PhotoScreenFragment : Fragment() {
    private lateinit var binding: FragmentPhotoScreenBinding
    private val viewmodel: PhotoScreenViewModel by lazy {
        ViewModelProvider(this)[PhotoScreenViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showCurrentDateText()
        viewmodel.getImage()
        observable()
    }

    private fun showCurrentDateText() {
        var date = Calendar.getInstance().time

        var dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        var textDate = buildString {
            append("Foto Motivacional do Dia | ")
            append(dateTimeFormat.format(date))
        }
        binding.tvDate.text = textDate
    }

    private fun showImage(image: Image) {

        Picasso.get().load(image.src).into(binding.ivPhoto)
        binding.ivPhoto.contentDescription = image.alt
    }

    private fun observable() {
        viewmodel.pexelsState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    showImage(it.data)
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}