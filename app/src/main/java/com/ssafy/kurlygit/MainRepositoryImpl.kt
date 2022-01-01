package com.ssafy.kurlygit

class MainRepositoryImpl(private val service : RepositoryService): MainRepository {
    override suspend fun getRepositories(search: String): Repositories = service.getRepositories(search, ApplicationClass.nowPage)

}