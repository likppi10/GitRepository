package com.ssafy.kurlygit.ui.view.data.model

data class Repositories(
    val total_count :  Int,
    val items :  MutableList<Repository>,
){
    constructor(): this(0, mutableListOf())
}
