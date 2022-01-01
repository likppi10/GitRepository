package com.ssafy.kurlygit.data.repository

import com.ssafy.kurlygit.ui.view.data.model.Repositories

interface MainRepository {

    suspend fun getRepositories(search: String): Repositories
}