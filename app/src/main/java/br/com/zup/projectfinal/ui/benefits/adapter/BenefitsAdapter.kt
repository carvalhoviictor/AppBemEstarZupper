package br.com.zup.projectfinal.ui.benefits.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.projectfinal.databinding.BenefitItemBinding
import br.com.zup.projectfinal.domain.model.Benefit
import br.com.zup.projectfinal.utils.BENEFIT_GYMPASS_URL
import br.com.zup.projectfinal.utils.BENEFIT_MOVIMENTE_URL
import br.com.zup.projectfinal.utils.BENEFIT_REINTEGRAR_URL
import br.com.zup.projectfinal.utils.BENEFIT_TELAVITA_URL

class BenefitsAdapter(
    private var benefitsList: MutableList<Benefit>,
    private val clickBenefit: (url: String) -> Unit,
) : RecyclerView.Adapter<BenefitsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            BenefitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val benefit = benefitsList[position]
        holder.showBenefitInfo(benefit)
        holder.binding.benefit.setOnClickListener {
            when(position){
                0 -> clickBenefit(BENEFIT_MOVIMENTE_URL)
                1 -> clickBenefit(BENEFIT_TELAVITA_URL)
                2 -> clickBenefit(BENEFIT_GYMPASS_URL)
                3 -> clickBenefit(BENEFIT_REINTEGRAR_URL)
            }
        }
    }

    override fun getItemCount() = benefitsList.size

    fun updateBenefitsList(newList: MutableList<Benefit>) {
        benefitsList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: BenefitItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun showBenefitInfo(benefit: Benefit) {
            binding.tvNameBenefit.text = benefit.title
            binding.ivLogoBenefit.setImageResource(benefit.image)
        }
    }
}