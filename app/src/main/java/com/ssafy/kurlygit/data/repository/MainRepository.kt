package com.ssafy.kurlygit.data.repository

import com.ssafy.kurlygit.ui.view.data.model.RepositoriesModel

interface MainRepository {

    suspend fun getRepositories(search: String): RepositoriesModel
}