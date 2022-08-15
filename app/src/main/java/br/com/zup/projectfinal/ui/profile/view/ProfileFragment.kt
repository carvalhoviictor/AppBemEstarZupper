package br.com.zup.projectfinal.ui.profile.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.projectfinal.R
import br.com.zup.projectfinal.databinding.FragmentProfileBinding
import br.com.zup.projectfinal.initial.InitialActivity
import br.com.zup.projectfinal.ui.profile.viewmodel.ProfileViewModel
import br.com.zup.projectfinal.utils.TITLE_PROFILE

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        actionBarAccess()
        showUserName()
        showUserEmail()

        binding.exitBtn.setOnClickListener {
            logout()
        }
    }

    private fun actionBarAccess() {
        (activity as InitialActivity).supportActionBar?.show()
        (activity as InitialActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as InitialActivity).supportActionBar?.title = TITLE_PROFILE
    }

    private fun showUserName() {
        val userName = buildString {
            append("Nome: ")
            append(viewModel.getUserName())
        }
        binding.tvUserName.text = userName
    }

    private fun showUserEmail() {
        val userEmail = buildString {
            append("E-mail: ")
            append(viewModel.getUserEmail())
        }
        binding.tvUserEmail.text = userEmail
    }

    private fun logout() {
        viewModel.logout()
        this.onDestroy()
        navigateToLoginFragment()
    }

    private fun navigateToLoginFragment() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_profileFragment_to_loginFragment)
    }
}