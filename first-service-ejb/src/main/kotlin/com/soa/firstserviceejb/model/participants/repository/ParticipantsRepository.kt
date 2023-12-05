package com.soa.firstserviceejb.model.participants.repository

import com.soa.firstserviceejb.model.participants.entity.Participants
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional

@ApplicationScoped
open class ParticipantsRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Transactional
    open fun save(participants: Participants): Participants {
        entityManager.persist(participants)
        return participants
    }
}