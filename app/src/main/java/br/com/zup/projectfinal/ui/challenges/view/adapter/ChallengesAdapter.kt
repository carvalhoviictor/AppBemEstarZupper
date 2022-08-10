package br.com.zup.projectfinal.ui.challenges.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.projectfinal.domain.model.ChallengeModel
import br.com.zup.projectfinal.databinding.ChallengeItemBinding
class ChallengesAdapter(
    private var challengesList: MutableList<ChallengeModel>
): RecyclerView.Adapter<ChallengesAdapter.ViewHolder>() {

    class ViewHolder(val binding: ChallengeItemBinding): RecyclerView.ViewHolder(binding.root){
        fun showChallenge(challengeModel: ChallengeModel){
            binding.tvMessageBody.text = challengeModel.challengeName
            binding.tvNumbPoints.text = challengeModel.challengePoints.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChallengeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val challenge = challengesList[position]
        holder.showChallenge(challenge)

        val checked: Boolean = holder.binding.cbCheck.isChecked
        if(checked){
            challenge.checkBox = checked
        }
        notifyItemChanged(position)
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