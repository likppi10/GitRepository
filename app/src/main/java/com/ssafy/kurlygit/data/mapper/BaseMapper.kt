package com.ssafy.kurlygit.data.mapper

interface BaseMapper<Entity, DTO> {
    fun toModel(entity: Entity): DTO
    //fun toEntity(dto: DTO): Entity
}