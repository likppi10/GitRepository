package com.ssafy.kurlygit.util

import androidx.recyclerview.widget.DiffUtil
import com.ssafy.kurlygit.ui.view.data.model.RepositoryModel

object Diff: DiffUtil.ItemCallback<RepositoryModel>() {

    override fun areItemsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean {
        return oldItem == newItem
    }
}
