package br.com.zup.projectfinal.ui.challenges.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.projectfinal.R
import br.com.zup.projectfinal.databinding.FragmentChallengesBinding
import br.com.zup.projectfinal.ui.InitialActivity
import br.com.zup.projectfinal.ui.challenges.view.adapter.ChallengesAdapter
import br.com.zup.projectfinal.ui.challenges.viewmodel.ChallengesViewModel
import br.com.zup.projectfinal.ui.viewstate.ViewState
import br.com.zup.projectfinal.utils.TITLE_DESAFIOS

class ChallengesFragment : Fragment() {
    private lateinit var binding: FragmentChallengesBinding

    private val viewModel: ChallengesViewModel by lazy {
        ViewModelProvider(this)[ChallengesViewModel::class.java]
    }

    private val challengesAdapter: ChallengesAdapter by lazy {
        ChallengesAdapter(arrayListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChallengesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        actionBarAccess()
        showUserData()

        viewModel.setChallengesList()
        viewModel.getFourRandomChallenges()
//        initObserver()
        showChallengesRecyclerView()
    }

    private fun actionBarAccess() {
        (activity as InitialActivity).supportActionBar?.show()
        (activity as InitialActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as InitialActivity).supportActionBar?.title = TITLE_DESAFIOS
    }

    private fun showUserData() {
        val name = viewModel.getUserName()
        binding.tvHelloZupper.text = getString(R.string.texto_home, name)
    }

    private fun navigateToLoginFragment() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_challengesFragment_to_loginFragment)
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

    private fun showChallengesRecyclerView() {
        binding.rvChallenges.adapter = challengesAdapter
        binding.rvChallenges.layoutManager = LinearLayoutManager(context)
    }

    private fun initObserver() {
        viewModel.challengesListState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    challengesAdapter.updateNotesList(it.data.toMutableList())
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}