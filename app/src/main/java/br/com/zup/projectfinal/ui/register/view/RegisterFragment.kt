package br.com.zup.projectfinal.ui.register.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.projectfinal.R
import br.com.zup.projectfinal.databinding.FragmentRegisterBinding
import br.com.zup.projectfinal.domain.model.User
import br.com.zup.projectfinal.initial.InitialActivity
import br.com.zup.projectfinal.ui.register.viewmodel.RegisterViewModel
import br.com.zup.projectfinal.utils.*

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as InitialActivity).supportActionBar?.hide()

        binding.bvRegisterNow.setOnClickListener {
            hideKeyboard()
            val user = getData()
            if (user != null) {
                viewModel.validateDataUser(user)
            } else {
                showFieldsError()
            }
        }

        binding.tvInformation.setOnClickListener {
            returnToLogin()
        }

        initObservers()
    }

    private fun getData(): User? {
        val username = binding.etUserName.text.toString()
        val userEmail = binding.etUserEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val passwordConfirm = binding.etPasswordConfirmation.text.toString()

        if (username.isNotEmpty() && userEmail.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()) {
            return User(
                name = username,
                email = userEmail,
                password = password,
                passwordConfirmation = passwordConfirm
            )
        }
        return null
    }

    private fun initObservers() {
        viewModel.registerState.observe(this.viewLifecycleOwner) {
            goToLogin(it)
        }

        viewModel.errorState.observe(this.viewLifecycleOwner) {
            if (it == EMAIL_ZUP_ERROR)
                binding.etUserEmail.error = EMAIL_ZUP_ERROR
        }
    }

    private fun goToLogin(user: User) {
        val bundle = bundleOf(USER_KEY to user)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_registerFragment_to_loginFragment, bundle)
    }

    private fun returnToLogin() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun showFieldsError() {
        if (binding.etUserName.text.toString().isEmpty()) {
            binding.etUserName.error = NAME_REQUIRED_FIELD
        }

        if (binding.etUserEmail.text.toString().isEmpty()) {
            binding.etUserEmail.error = EMAIL_REQUIRED_FIELD
        }

        if (binding.etPassword.text.toString().isEmpty()) {
            binding.etPassword.error = PASSWORD_REQUIRED_FIELD
        }

        if (binding.etPasswordConfirmation.text.toString().isEmpty()) {
            binding.etPasswordConfirmation.error = PASSWORD_CONFIRMATION_REQUIRED_FIELD
        }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
}