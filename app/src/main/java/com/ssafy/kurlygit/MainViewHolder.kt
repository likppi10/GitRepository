package com.ssafy.kurlygit

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.kurlygit.databinding.ViewholderItemBinding

class MainViewHolder(private val binding: ViewholderItemBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: Repository) {

        with(binding){
            repo = item
            executePendingBindings()
        }
//        binding.tvRepTitle.text = item.name
//        binding.tvRepSubtitle.text = item.description
//
//        if(item.private){
//            binding.tvRepPriv.text = "Private"
//        }else{
//            binding.tvRepPriv.text = "Public"
//        }
//
//        binding.tvRepFork.text = "Fork : ${item.forks_count.toString()}"
//        binding.tvRepStar.text = "Star : ${item.stargazers_count.toString()}"
    }
}