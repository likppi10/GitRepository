package com.ssafy.kurlygit.util

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.kurlygit.ui.view.data.model.Repository

object Diff: DiffUtil.ItemCallback<Repository>() {

    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }
}
