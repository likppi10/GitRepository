package com.ssafy.kurlygit.ui.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.kurlygit.ui.view.data.model.Repository
import com.ssafy.kurlygit.databinding.ViewholderItemBinding

class MainViewHolder(private val binding: ViewholderItemBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: Repository) {

        with(binding){
            repo = item
            executePendingBindings()
        }
    }
}