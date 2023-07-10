package com.pc.studyjapanesen5.data.model.entity

interface DataMapper<Entity, Model> {
    fun toModel(entity: Entity) : Model

    fun toEntity(model: Model) : Entity
}

