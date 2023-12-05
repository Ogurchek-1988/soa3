package com.soa.firstserviceejb.model.musicBand.repository

import com.soa.common.dto.MusicGenre
import com.soa.firstserviceejb.model.musicBand.entity.MusicBand
import com.soa.firstserviceejb.model.musicBand.entity.MusicBandArray
import com.soa.firstserviceejb.model.musicBand.entity.MusicBandId
import com.soa.firstserviceejb.util.Filter
import com.soa.firstserviceejb.util.Sort
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Query
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Order
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import jakarta.transaction.Transactional
import java.sql.Date
import kotlin.math.ceil


@ApplicationScoped
open class MusicBandRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Transactional
    open fun save(musicBand: MusicBand): MusicBand {
        println("addBand - repo")
        entityManager.persist(musicBand)
        println("addBand - end repo")
        return musicBand
    }

    @Transactional
    open fun update(musicBand: MusicBand): MusicBand {
        entityManager.merge(musicBand)
        return musicBand
    }

    @Transactional
    open fun findById(bandId: MusicBandId): MusicBand =
        entityManager.find(MusicBand::class.java, bandId)

    @Transactional
    open fun deleteById(musicBand: MusicBand) =
        entityManager.remove(if (entityManager.contains(musicBand)) musicBand else entityManager.merge(musicBand))

    @Transactional
    open fun findSumNumberParticipantsAllRecords(): Long {
        return entityManager
            .createQuery("select sum(m.numberOfParticipants) from MusicBand m")
            .singleResult as Long
    }

    @Transactional
    open fun countMusicBandsByNumberOfParticipantsIsLessThan(participantsNumber: Int): Long {
        val tmp = entityManager
            .createQuery("select count(m.numberOfParticipants) from MusicBand m where numberOfParticipants < :participantsNumber")
            .setParameter("participantsNumber", participantsNumber)
            .singleResult as Long
        println("countMusicBandsByNumberOfParticipantsIsLessThan")
        println(tmp)
        return tmp
    }

    @Transactional
    open fun findMusicBandsByMusicGenreIsLessThan(genre: MusicGenre): List<MusicBand> {
        val tmp =  entityManager
            .createQuery("select m from MusicBand m where musicGenre < :genre")
            .setParameter("genre", genre)
            .resultList as List<MusicBand>

        println("findMusicBandsByMusicGenreIsLessThan")
        println(tmp)
        return tmp
    }

    @Transactional
    open fun test(sortList: List<Sort>, filtersList: List<Filter>, page: Int?, size: Int?): MusicBandArray {
        println("jopa in repo")
        val criteriaBuilder = entityManager.criteriaBuilder
        val flatQuery: CriteriaQuery<MusicBand> = criteriaBuilder.createQuery(MusicBand::class.java)
        val root: Root<MusicBand> = flatQuery.from(MusicBand::class.java)

        val select = flatQuery.select(root)
        var predicates: ArrayList<Predicate> = ArrayList()

        if (filtersList.isNotEmpty()) {
            predicates = ArrayList()
            for (filter in filtersList) {
                when (filter.filteringOperation) {
                    FilteringOperation.EQ -> if (filter.nestedName != null) {
                        predicates.add(
                            criteriaBuilder.equal(
                                root.get<MusicBand>(filter.fieldName).get<MusicBand>(filter.nestedName),
                                getTypedFieldValue(filter.fieldName, filter.fieldValue)
                            )
                        )
                    } else {
                        predicates.add(
                            criteriaBuilder.equal(
                                root.get<MusicBand>(filter.fieldName),
                                getTypedFieldValue(filter.fieldName, filter.fieldValue)
                            )
                        )
                    }

                    FilteringOperation.NEQ -> if (filter.nestedName != null) {
                        predicates.add(
                            criteriaBuilder.notEqual(
                                root.get<MusicBand>(filter.fieldName).get<MusicBand>(filter.nestedName),
                                getTypedFieldValue(filter.fieldName, filter.fieldValue)
                            )
                        )
                    } else {
                        predicates.add(
                            criteriaBuilder.notEqual(
                                root.get<MusicBand>(filter.fieldName),
                                getTypedFieldValue(filter.fieldName, filter.fieldValue)
                            )
                        )
                    }

                    FilteringOperation.GT -> if (filter.nestedName != null) {
                        predicates.add(
                            criteriaBuilder.greaterThan(
                                root.get<MusicBand>(filter.fieldName).get(filter.nestedName),
                                filter.fieldValue!!
                            )
                        )
                    } else {
                        predicates.add(
                            criteriaBuilder.greaterThan(
                                root.get(filter.fieldName),
                                filter.fieldValue!!
                            )
                        )
                    }

                    FilteringOperation.LT -> if (filter.nestedName != null) {
                        predicates.add(
                            criteriaBuilder.lessThan(
                                root.get<MusicBand>(filter.fieldName).get(filter.nestedName),
                                filter.fieldValue!!
                            )
                        )
                    } else {
                        predicates.add(
                            criteriaBuilder.lessThan(
                                root.get(filter.fieldName),
                                filter.fieldValue!!
                            )
                        )
                    }

                    FilteringOperation.GTE -> if (filter.nestedName != null) {
                        predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                root.get<MusicBand>(filter.fieldName).get(filter.nestedName),
                                filter.fieldValue!!
                            )
                        )
                    } else {
                        predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                root.get(filter.fieldName),
                                filter.fieldValue!!
                            )
                        )
                    }

                    FilteringOperation.LTE -> if (filter.nestedName != null) {
                        predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                root.get<MusicBand>(filter.fieldName).get(filter.nestedName),
                                filter.fieldValue!!
                            )
                        )
                    } else {
                        predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                root.get(filter.fieldName),
                                filter.fieldValue!!
                            )
                        )
                    }

                    FilteringOperation.UNDEFINED -> {}
                }
            }
            select.where(criteriaBuilder.and(*predicates.toTypedArray<Predicate>()))
        }

        if (sortList.isNotEmpty()) {
            val orderList: ArrayList<Order> = ArrayList()

            for (sortItem in sortList) {
                if (sortItem.desc!!) {
                    if (sortItem.nestedName != null) {
                        orderList.add(criteriaBuilder.desc(root.get<MusicBand>(sortItem.fieldName).get<MusicBand>(sortItem.nestedName)))
                    } else {
                        orderList.add(criteriaBuilder.desc(root.get<MusicBand>(sortItem.fieldName)))
                    }
                } else {
                    if (sortItem.nestedName != null) {
                        orderList.add(criteriaBuilder.asc(root.get<MusicBand>(sortItem.fieldName).get<MusicBand>(sortItem.nestedName)))
                    } else {
                        orderList.add(criteriaBuilder.asc(root.get<MusicBand>(sortItem.fieldName)))
                    }
                }
            }
            select.orderBy(orderList)
        }

        val typedQuery = entityManager.createQuery(select)
        val bandList: List<MusicBand>
        val totalCount = typedQuery.resultList.size
        val ret: MusicBandArray = MusicBandArray()

        if (page != null && size != null) {
            typedQuery.setFirstResult((page - 1) * size)
            typedQuery.setMaxResults(size)

            bandList = typedQuery.resultList

            var countResult: Long = 0

            countResult = if (predicates.isNotEmpty()) {
                val queryTotal: Query = entityManager.createQuery("SELECT COUNT(m.id) FROM MusicBand m")
                queryTotal.singleResult as Long
            } else {
                val qb = entityManager.criteriaBuilder
                val cq = qb.createQuery(Long::class.java)
                cq.select(qb.count(cq.from(MusicBand::class.java)))
                cq.where(criteriaBuilder.and(*predicates.toTypedArray<Predicate>()))
                entityManager.createQuery<Long>(cq).singleResult
            }

            ret.page = page.toLong()
            ret.pageSize = size.toLong()
            ret.pagesTotal = ceil(totalCount * 1.0 / size).toLong()
            ret.bands = bandList as ArrayList<MusicBand>
        } else {
            bandList = typedQuery.resultList;

            ret.page = 1
            ret.pageSize = totalCount.toLong()
            ret.pagesTotal = 1
            ret.bands = bandList as ArrayList<MusicBand>
        }

        return ret
    }

    open fun getTypedFieldValue(fieldName: String?, fieldValue: String?): Any? {
        return when (fieldName) {
            "id", "numberOfParticipants" -> fieldValue!!.toInt()
            "creationDate" -> Date.valueOf(fieldValue)
            "musicGenre" -> MusicGenre.valueOf(fieldValue!!)
            "locationX" -> fieldValue!!.toFloat()
            "coordinateX", "coordinateY", "locationY" -> fieldValue!!.toDouble()
            else -> null
        }
    }
}