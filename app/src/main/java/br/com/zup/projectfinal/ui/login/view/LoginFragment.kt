package br.com.zup.projectfinal.ui.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.projectfinal.R
import br.com.zup.projectfinal.databinding.FragmentLoginBinding
import br.com.zup.projectfinal.domain.model.User
import br.com.zup.projectfinal.ui.InitialActivity
import br.com.zup.projectfinal.ui.login.viewmodel.LoginViewModel
import br.com.zup.projectfinal.utils.LOGIN_ERROR_MESSAGE
import br.com.zup.projectfinal.utils.USER_KEY

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as InitialActivity).supportActionBar?.hide()

        binding.bvRegisterNow.setOnClickListener {
            goToRegister()
        }

        binding.bvLogin.setOnClickListener {
            val user = getDataUser()
            viewModel.validateDataUser(user)
        }

        initObservers()
    }

    private fun getDataUser(): User {
        return User(
            email = binding.etUserEmail.text.toString(),
            password = binding.etPassword.text.toString()
        )
    }

    private fun goToRegister() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun goToHome(user: User) {
        val bundle = bundleOf(USER_KEY to user)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_loginFragment_to_homeFragment, bundle)
    }

    private fun initObservers() {
        viewModel.loginState.observe(this.viewLifecycleOwner) {
            goToHome(it)
        }

        viewModel.errorState.observe(this.viewLifecycleOwner) {
            Toast.makeText(context, LOGIN_ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
        }
    }
}