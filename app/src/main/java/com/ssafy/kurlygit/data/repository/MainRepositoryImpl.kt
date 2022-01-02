package com.ssafy.kurlygit.data.repository

import com.ssafy.kurlygit.ApplicationClass
import com.ssafy.kurlygit.data.mapper.RepositoriesMapper
import com.ssafy.kurlygit.data.api.RepositoryService
import com.ssafy.kurlygit.ui.view.data.model.RepositoriesModel

class MainRepositoryImpl(private val service : RepositoryService): MainRepository {

    override suspend fun getRepositories(search: String): RepositoriesModel {
        return service.getRepositories(search, ApplicationClass.nowPage).let {
            RepositoriesMapper.toModel(it)
        }
    }
}