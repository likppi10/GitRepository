package com.ssafy.kurlygit.data.repository

import com.ssafy.kurlygit.ApplicationClass
import com.ssafy.kurlygit.data.api.RepositoryService
import com.ssafy.kurlygit.ui.view.data.model.Repositories

class MainRepositoryImpl(private val service : RepositoryService): MainRepository {
    override suspend fun getRepositories(search: String): Repositories = service.getRepositories(search,
        ApplicationClass.nowPage
    )

}