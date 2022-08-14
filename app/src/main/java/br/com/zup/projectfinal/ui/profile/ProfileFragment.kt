package br.com.zup.projectfinal.ui.profile

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.projectfinal.R
import br.com.zup.projectfinal.databinding.FragmentProfileBinding
import br.com.zup.projectfinal.ui.InitialActivity
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
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        actionBarAccess()
        showUserName()
        showUserEmail()
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
            append("Nome: ")
            append(viewModel.getUserEmail())
        }
        binding.tvUserName.text = userEmail
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