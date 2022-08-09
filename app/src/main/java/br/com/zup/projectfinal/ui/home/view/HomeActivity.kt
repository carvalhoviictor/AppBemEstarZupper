package br.com.zup.projectfinal.ui.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.projectfinal.R
import br.com.zup.projectfinal.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}