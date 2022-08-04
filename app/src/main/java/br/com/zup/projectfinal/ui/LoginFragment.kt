package br.com.zup.projectfinal.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.projectfinal.R
import br.com.zup.projectfinal.databinding.FragmentLoginBinding
import br.com.zup.projectfinal.utils.TAG
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

//        val googleSignInOptions =
//            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
//                .requestIdToken(R.string.default_web_client_id.toString())
//                .requestEmail()
//                .build()
//        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
//
//        firebaseAuth = FirebaseAuth.getInstance()
//        checkUser()
//
//        binding.sbGoogle.setOnContextClickListener
//
//
//    private fun checkUser() {
//
//    }
//
//    private fun goToHome() {
//        NavHostFragment.findNavController(this)
//            .navigate(R.id.action_loginFragment_to_homeFragment)
//    }
}