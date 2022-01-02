package com.ssafy.kurlygit.data.mapper

import android.util.Log
import com.ssafy.kurlygit.entity.RepositoriesEntity
import com.ssafy.kurlygit.ui.view.data.model.RepositoriesModel
import com.ssafy.kurlygit.ui.view.data.model.RepositoryModel

object RepositoriesMapper: BaseMapper<RepositoriesEntity, RepositoriesModel> {

    /* DB에서의 값들이 매칭되는 Entity를 레이어 간에서 사용하기 위해 Model로 바꿔줍니다.
    * 본 어플에서는 불러들여 읽기만 사용하므로, Model -> Entity로 변환하는 것은 생략합니다.
    * 또한, null 값 처리를 여기서 해줘서 다른 도메인에서는 이를 감안하지 않아도 됩니다. */
    override fun toModel(entity: RepositoriesEntity): RepositoriesModel {
        var repositoriesModel = RepositoriesModel(
            total_count = entity.total_count ?: 0,
            items = mutableListOf<RepositoryModel>().apply {
                entity.items.forEach {
                    this.add(
                        RepositoryModel(
                            id = it.id,
                            name = it.name ?: "" ,
                            private = it.private,
                            description = it.description ?: "",
                            stargazers_count = it.stargazers_count ?: 0,
                            forks_count = it.forks_count ?: 0)
                    )

                }
            }
        )
        return repositoriesModel
    }

}