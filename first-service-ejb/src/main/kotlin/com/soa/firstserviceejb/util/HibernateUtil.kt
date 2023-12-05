package com.soa.firstserviceejb.util

import com.soa.firstserviceejb.model.musicBand.entity.MusicBand
import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import org.hibernate.service.ServiceRegistry
import java.util.*


class HibernateUtil {

    private lateinit var sessionFactory: SessionFactory

    fun getSessionFactory(): SessionFactory = sessionFactory

    fun getCurrentSession(): Session = sessionFactory.currentSession

    fun closeSessionSilently() {
        val session = sessionFactory.currentSession
        try {
            if (session != null && session.isOpen)
                session.close()
        } catch (e: HibernateException) {
            e.stackTrace
        }
    }

    fun initialize(properties: Properties) {
        val config: Configuration = Configuration()
        config.addAnnotatedClass(MusicBand::class.java)
        val enumeration = properties.propertyNames()
        while (enumeration.hasMoreElements()) {
            val key = enumeration.nextElement()
            val value = properties.getProperty(key.toString())
            config.setProperty(key.toString(), value)
        }
        val serviceRegistry: ServiceRegistry = StandardServiceRegistryBuilder().applySettings(config.properties).build()
        sessionFactory = config.buildSessionFactory(serviceRegistry)
    }

}