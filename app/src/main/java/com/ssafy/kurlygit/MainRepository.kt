package com.ssafy.kurlygit

interface MainRepository {

    suspend fun getRepositories(search: String): Repositories
}