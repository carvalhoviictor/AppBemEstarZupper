package br.com.zup.projectfinal.ui.challenges.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.com.zup.projectfinal.databinding.FragmentChallengesBinding
import br.com.zup.projectfinal.ui.InitialActivity
import br.com.zup.projectfinal.ui.challenges.view.adapter.ChallengesAdapter
import br.com.zup.projectfinal.ui.challenges.viewmodel.ChallengesViewModel
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
        (activity as InitialActivity).supportActionBar?.title = TITLE_DESAFIOS

        viewModel.setChallengesList()
        viewModel.getFourRandomChallenges()
        initObserver()

    }
}