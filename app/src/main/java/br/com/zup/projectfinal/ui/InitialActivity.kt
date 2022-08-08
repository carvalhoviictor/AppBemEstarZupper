package br.com.zup.projectfinal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.projectfinal.databinding.ActivityInitialBinding

class InitialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}