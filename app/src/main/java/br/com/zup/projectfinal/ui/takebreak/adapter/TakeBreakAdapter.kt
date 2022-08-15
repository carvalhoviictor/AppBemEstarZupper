package br.com.zup.projectfinal.ui.takebreak.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.projectfinal.databinding.TakeBreakItemBinding
import br.com.zup.projectfinal.domain.model.TakeBreakModel

class TakeBreakAdapter(
    private var takeBreakList: MutableList<TakeBreakModel>
) : RecyclerView.Adapter<TakeBreakAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TakeBreakItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val takeBreak = takeBreakList[position]
        holder.adicionarInformacoesView(takeBreak)
    }

    override fun getItemCount() = takeBreakList.size

    fun updateTakeBreak(newList: MutableList<TakeBreakModel>) {
        takeBreakList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: TakeBreakItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun adicionarInformacoesView(takeBreak: TakeBreakModel) {
            binding.ivTakeBreak.setImageResource(takeBreak.image)
            binding.tvTakeBreak.text = takeBreak.title
            binding.tvTakeBreak2.text = takeBreak.subtitle
        }
    }
}