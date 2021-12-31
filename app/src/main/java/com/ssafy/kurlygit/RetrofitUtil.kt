package com.ssafy.kurlygit

class RetrofitUtil {
    companion object{
        val repositoryService: RepositoryAPI = ApplicationClass.retrofit.create(RepositoryAPI::class.java)
    }
}