package com.ssafy.kurlygit

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.kurlygit.databinding.ViewholderItemBinding

class MainViewHolder(private val binding: ViewholderItemBinding) : RecyclerView.ViewHolder(binding.root) {


    /*
    fun bind(item: result) {
        view.result.title = item.title
        view.result.subtitle = item.subtitle
        view.result.star = item.star
        view.result.fork = item.fork
    }
    */

    @SuppressLint("SetTextI18n")
    fun bind(item: Repository) {
        binding.tvRepTitle.text = item.name
        binding.tvRepSubtitle.text = item.description
        if(item.private){
            binding.tvRepPriv.text = "Private"
        }else{
            binding.tvRepPriv.text = "Public"
        }

        binding.tvRepFork.text = item.forks_count.toString()
        binding.tvRepStar.text = item.stargazers_count.toString()
    }
}