package com.ssafy.kurlygit.data.mapper

import com.ssafy.kurlygit.RepositoriesEntity
import com.ssafy.kurlygit.ui.view.data.model.RepositoriesModel
import com.ssafy.kurlygit.ui.view.data.model.RepositoryModel

object RepositoriesMapper: BaseMapper<RepositoriesEntity, RepositoriesModel> {
    override fun toModel(entity: RepositoriesEntity): RepositoriesModel {
        return RepositoriesModel(
            total_count = entity.total_count,
            items = mutableListOf<RepositoryModel>().apply {
                entity.items.forEach {
                    this.add(
                        RepositoryModel(
                            it.id,
                            it.name,
                            it.private,
                            it.description,
                            it.stargazers_count,
                            it.forks_count)
                    )
                }
            }
        )
    }

//    override fun toEntity(model: Repositories): Entity {
//        return RepositoriesEntity( // TODO GET 함수에서는 쓸모가 없다?!
//        )
//    }
}