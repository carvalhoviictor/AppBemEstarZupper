package br.com.zup.projectfinal.ui.challenges.view.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.projectfinal.domain.model.ChallengeModel
import br.com.zup.projectfinal.databinding.ChallengeItemBinding
import br.com.zup.projectfinal.utils.*


class ChallengesAdapter(
    private var challengesList: MutableList<ChallengeModel>,
    private val onCheckboxClicked: (view: View, challenge: ChallengeModel) -> Unit
): RecyclerView.Adapter<ChallengesAdapter.ViewHolder>() {

    class ViewHolder(val binding: ChallengeItemBinding): RecyclerView.ViewHolder(binding.root){
        fun showChallenge(challengeModel: ChallengeModel){
            binding.tvMessageBody.text = challengeModel.challengeName
            binding.tvNumbPoints.text = challengeModel.challengePoints.toString()
            if(challengeModel.check){
                binding.cbCheck.isChecked = true
                binding.cbCheck.isEnabled = false
                binding.cbCheck.isClickable = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChallengeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pref = holder.binding.cbCheck.context.applicationContext.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        val prefEditor: SharedPreferences.Editor = pref.edit()

        val challenge = challengesList[position]
        holder.showChallenge(challenge)

        holder.binding.cbCheck.setOnClickListener {
            challenge.check = true
            onCheckboxClicked(holder.binding.cbCheck, challenge)
            holder.binding.cbCheck.isClickable = false

            if(position == 0){
                prefEditor.putBoolean(PREF_CHECKED_ONE, true)
            }
            if(position == 1){
                prefEditor.putBoolean(PREF_CHECKED_TWO, true)
            }
            if(position == 2){
                prefEditor.putBoolean(PREF_CHECKED_THREE, true)
            }
            if(position == 3){
                prefEditor.putBoolean(PREF_CHECKED_FOUR, true)
            }
        }
    }

    override fun getItemCount(): Int {
        return challengesList.size
    }

    fun updateNotesList(newList: MutableList<ChallengeModel>){
        if (challengesList.size == 0){
            challengesList = newList
        }else{
            challengesList = mutableListOf()
            challengesList.addAll(newList)
        }
        notifyDataSetChanged()
    }
}