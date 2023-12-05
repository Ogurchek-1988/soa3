package com.soa.firstserviceejb.model.grammy.repository

import com.soa.firstserviceejb.model.grammy.entity.Grammy
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional

@ApplicationScoped
open class GrammyRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Transactional
    open fun save(grammy: Grammy): Grammy {
        println("save")
        entityManager.persist(grammy)
        return grammy
    }
}