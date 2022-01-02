package com.ssafy.kurlygit.ui.view.data.model

data class RepositoriesModel(
    val total_count :  Int,
    val items :  MutableList<RepositoryModel>,
){
    constructor(): this(0, mutableListOf())
}
