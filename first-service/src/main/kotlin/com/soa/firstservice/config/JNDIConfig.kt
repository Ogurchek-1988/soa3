package com.soa.firstservice.config

import com.soa.firstserviceejb.service.GrammyService
import com.soa.firstserviceejb.service.MusicBandService
import jakarta.ws.rs.NotFoundException
import java.util.*
import javax.naming.Context
import javax.naming.InitialContext
import javax.naming.NamingException


object JNDIConfig {
    fun musicBandService(): MusicBandService {
        val jndiProps = Properties()
        jndiProps[Context.INITIAL_CONTEXT_FACTORY] = "org.wildfly.naming.client.WildFlyInitialContextFactory"
        /*jndiProps[Context.PROVIDER_URL] = "remote+https://localhost:8443"*/
        return try {
            val context: Context = InitialContext(jndiProps)
            context.lookup("ejb:/first-service-ejb-1.0-SNAPSHOT/MusicBandServiceImpl!com.soa.firstserviceejb.service.MusicBandService") as MusicBandService
        } catch (e: NamingException) {
            println("Unable to find bean")
            e.printStackTrace()
            throw NotFoundException()
        }
    }

    fun grammyService(): GrammyService {
        val jndiProps = Properties()
        jndiProps[Context.INITIAL_CONTEXT_FACTORY] = "org.wildfly.naming.client.WildFlyInitialContextFactory"
        /*jndiProps[Context.PROVIDER_URL] = "remote+http://localhost:8080"*/
        return try {
            val context: Context = InitialContext(jndiProps)
            context.lookup("ejb:/first-service-ejb-1.0-SNAPSHOT/GrammyServiceImpl!com.soa.firstserviceejb.service.GrammyService") as GrammyService
        } catch (e: NamingException) {
            println("Unable to find bean")
            e.printStackTrace()
            throw NotFoundException()
        }
    }
}