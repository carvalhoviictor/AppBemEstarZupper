package br.com.zup.projectfinal.ui.photoscreen.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.projectfinal.R
import br.com.zup.projectfinal.databinding.FragmentPhotoScreenBinding
import br.com.zup.projectfinal.domain.model.Image
import br.com.zup.projectfinal.ui.InitialActivity
import br.com.zup.projectfinal.ui.ViewState
import br.com.zup.projectfinal.ui.benefits.adapter.BenefitsAdapter
import br.com.zup.projectfinal.ui.benefits.viewmodel.BenefitsViewModel
import br.com.zup.projectfinal.ui.photoscreen.viewmodel.PhotoScreenViewModel
import br.com.zup.projectfinal.utils.TITLE_BSZ
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class PhotoScreenFragment : Fragment() {
    private lateinit var binding: FragmentPhotoScreenBinding

    private val benefitsAdapter: BenefitsAdapter by lazy {
        BenefitsAdapter(arrayListOf()
//            , this::goToWeb
        )
    }

    private val benefitsViewModel: BenefitsViewModel by lazy {
        ViewModelProvider(this)[BenefitsViewModel::class.java]
    }

    private val viewModel: PhotoScreenViewModel by lazy {
        ViewModelProvider(this)[PhotoScreenViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPhotoScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        actionBarAccess()
        showCurrentDateText()
        viewModel.getImage()
        observable()
    }

    override fun onResume() {
        super.onResume()
        benefitsViewModel.getAllBenefits()
        setBenefisRecyclerView()
    }

    private fun actionBarAccess() {
        (activity as InitialActivity).supportActionBar?.show()
        (activity as InitialActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as InitialActivity).supportActionBar?.title = TITLE_BSZ
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

    private fun navigateToLoginFragment() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_photoScreenFragment_to_loginFragment)
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

    private fun showImage(image: Image) {

        Picasso.get().load(image.src).into(binding.ivPhoto)
        binding.ivPhoto.contentDescription = image.alt
    }

    private fun observable() {
        viewModel.pexelsState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    showImage(it.data)
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

        benefitsViewModel.benefitResponse.observe(this.viewLifecycleOwner) {
            benefitsAdapter.updateBenefitsList(it.toMutableList())
        }
    }

    private fun setBenefisRecyclerView() {
        binding.rvBenefits.adapter = benefitsAdapter
        binding.rvBenefits.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
    }
}