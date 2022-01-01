package com.ssafy.kurlygit

class MainRepositoryImpl(): MainRepository {

    override suspend fun getRepositories(search: String): Repositories =
        RetrofitUtil.REPOSITORY_SERVICE.getRepositories(search, ApplicationClass.nowPage)
}