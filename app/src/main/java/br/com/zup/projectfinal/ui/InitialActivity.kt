package br.com.zup.projectfinal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.zup.projectfinal.R
import br.com.zup.projectfinal.databinding.ActivityInitialBinding
import br.com.zup.projectfinal.ui.challenges.view.ChallengesFragment
import br.com.zup.projectfinal.ui.notes.view.NotesFragment
import br.com.zup.projectfinal.ui.photoscreen.view.PhotoScreenFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class InitialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitialBinding

    private val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBarWithNavController(navController)
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> hideBottomNav()
                R.id.registerFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }


        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuChallengesPage -> {
                    navController.navigate(R.id.challengesFragment)
                    true
                }
                R.id.menuBreakPage -> {
                    navController.navigate(R.id.photoScreenFragment)
                    true
                }
                R.id.menuNotesPage -> {
                    navController.navigate(R.id.notesFragment)
                    true
                }
                else -> false
            }
        }
//        binding.bottomNavigation.setOnItemSelectedListener {
//
//            when(it.itemId){
//                R.id.menuChallengesPage -> replaceFragment(ChallengesFragment())
//                R.id.menuBreakPage -> replaceFragment(PhotoScreenFragment())
//                R.id.menuNotesPage -> replaceFragment(NotesFragment())
//
//                else -> {
//                }
//            }
//            true
//        }
//    }
//
//    private fun replaceFragment(fragment: Fragment){
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.nav_host_fragment,fragment)
//        fragmentTransaction.commit()
    }


    private fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavigation.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}