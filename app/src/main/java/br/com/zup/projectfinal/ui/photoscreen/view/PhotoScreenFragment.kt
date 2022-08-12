package br.com.zup.projectfinal.ui.photoscreen.view

import android.content.Intent
import android.net.Uri
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
import br.com.zup.projectfinal.ui.takebreak.adapter.TakeBreakAdapter
import br.com.zup.projectfinal.ui.takebreak.viewmodel.TakeBreakViewModel
import br.com.zup.projectfinal.utils.TITLE_BSZ
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class PhotoScreenFragment : Fragment() {
    private lateinit var binding: FragmentPhotoScreenBinding

    private val takeBreakAdapter: TakeBreakAdapter by lazy {
        TakeBreakAdapter(arrayListOf())
    }

    private val takeBreakViewModel: TakeBreakViewModel by lazy {
        ViewModelProvider(this)[TakeBreakViewModel::class.java]
    }

    private val benefitsAdapter: BenefitsAdapter by lazy {
        BenefitsAdapter(
            arrayListOf(), this::goToWeb)
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
        takeBreakViewModel.getTakeBreak()
        setTakeBreakRecyclerView()
        benefitsViewModel.getAllBenefits()
        setBenefisRecyclerView()
    }

    private fun actionBarAccess() {
        (activity as InitialActivity).supportActionBar?.show()
        (activity as InitialActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as InitialActivity).supportActionBar?.title = TITLE_BSZ
    }

    private fun showCurrentDateText() {

        val date = Calendar.getInstance().time

        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        val textDate = buildString {
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
        takeBreakViewModel.takeBreakResponse.observe(this.viewLifecycleOwner) {
            takeBreakAdapter.updateTakeBreak(it.toMutableList())
        }

        benefitsViewModel.benefitResponse.observe(this.viewLifecycleOwner) {
            benefitsAdapter.updateBenefitsList(it.toMutableList())
        }
    }

    private fun setTakeBreakRecyclerView() {
        binding.rvTakeBreak.adapter = takeBreakAdapter
        binding.rvTakeBreak.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
    }

    private fun setBenefisRecyclerView() {
        binding.rvBenefits.adapter = benefitsAdapter
        binding.rvBenefits.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
    }

    private fun goToWeb(uri: String) {
        var url = uri
        if (!url.startsWith("https://" ) && !url.startsWith("http://")){
            url = "https://$uri"
        }
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}