package com.ssafy.kurlygit

data class Repository(
    val name :  String,
    val private: Boolean,
    val description: String,
    val stargazers_count: Int,
    val forks_count: Int,
) {
    constructor(): this("",true,"",0,0)
    //constructor(): this("")
}