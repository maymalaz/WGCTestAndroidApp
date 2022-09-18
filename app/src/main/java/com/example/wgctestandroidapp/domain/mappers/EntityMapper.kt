package com.example.wgctestandroidapp.domain.mappers

interface Entity
interface NetworkModel: Entity
interface LocalModel: Entity

interface EntityMapper <Entity, DomainModel>
        where Entity: com.example.wgctestandroidapp.domain.mappers.Entity,
              DomainModel: com.example.wgctestandroidapp.data.common.base.DomainModel {
    fun mapToDomain(apiEntity: Entity): DomainModel
    fun mapFromDomain(domainModel: DomainModel): Entity
}