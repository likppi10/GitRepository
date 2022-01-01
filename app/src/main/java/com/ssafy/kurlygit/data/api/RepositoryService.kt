package com.ssafy.kurlygit.data.api
import com.ssafy.kurlygit.ui.view.data.model.Repositories
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Repositories
}