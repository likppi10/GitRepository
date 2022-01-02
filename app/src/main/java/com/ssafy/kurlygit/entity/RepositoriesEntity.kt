package com.ssafy.kurlygit.entity

data class RepositoriesEntity(
    val total_count :  Int,
    val items :  MutableList<RepositoryEntity>,
)class RepositoryEntity(
    val id: Int,
    val name :  String,
    val private: Boolean,
    val description: String,
    val stargazers_count: Int,
    val forks_count: Int,
)
