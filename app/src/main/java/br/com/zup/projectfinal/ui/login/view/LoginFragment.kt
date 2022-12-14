package br.com.zup.projectfinal.ui.login.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.projectfinal.R
import br.com.zup.projectfinal.databinding.FragmentLoginBinding
import br.com.zup.projectfinal.domain.model.User
import br.com.zup.projectfinal.initial.InitialActivity
import br.com.zup.projectfinal.ui.login.viewmodel.LoginViewModel
import br.com.zup.projectfinal.utils.*

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as InitialActivity).supportActionBar?.hide()
        registerButton()
        loginButton()
        initObservers()
    }

    private fun registerButton() {
        binding.bvRegisterNow.setOnClickListener {
            goToRegister()
        }
    }

    private fun loginButton() {
        binding.bvLogin.setOnClickListener {
            hideKeyboard()
            if (validateField()) {
                val user = getDataUser()
                viewModel.validateDataUser(user)
            }
        }
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
            .navigate(R.id.action_loginFragment_to_challengesFragment, bundle)
    }

    private fun initObservers() {
        viewModel.loginState.observe(this.viewLifecycleOwner) {
            goToHome(it)
        }

        viewModel.errorState.observe(this.viewLifecycleOwner) {
            hideKeyboard()
            Toast.makeText(context, LOGIN_PASSWORD_INCORRECT, Toast.LENGTH_SHORT).show()
        }
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun validateField(): Boolean {
        return when {
            (binding.etUserEmail.text.toString().isEmpty() && binding.etPassword.text.toString()
                .isEmpty()) -> {
                binding.etUserEmail.error = EMAIL_REQUIRED_FIELD
                binding.etPassword.error = PASSWORD_REQUIRED_FIELD
                false
            }
            binding.etUserEmail.text.toString().isEmpty() -> {
                binding.etUserEmail.error = EMAIL_REQUIRED_FIELD
                false
            }
            binding.etPassword.text.toString().isEmpty() -> {
                binding.etPassword.error = PASSWORD_REQUIRED_FIELD
                false
            }
            else -> true
        }
    }
}