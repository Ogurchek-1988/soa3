package com.soa.secondservice.service

import com.soa.common.dto.ParticipantDto
import com.soa.secondservice.config.SslSettings
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.springframework.stereotype.Service


@Service
class FirstServiceClient {

    private val client = HttpClient(Apache) {
        install(Logging)
        defaultRequest {
            url("https://192.168.48.41:8443/first-service/api/grammy/")
        }
        engine {
            customizeClient {
                sslContext = SslSettings.getSslContext()
                setSSLHostnameVerifier(NoopHostnameVerifier())
            }
        }
    }

    suspend fun nominateBand(
        bandId: Int,
        genreId: Int
    ) = client.post {
        url {
            appendPathSegments(bandId.toString())
            appendPathSegments("nominate")
            appendPathSegments(genreId.toString())
        }
    }

    suspend fun test() = client.get {
        url {
            appendPathSegments("test")
        }
    }

    suspend fun addPart(
        participantDto: ParticipantDto
    ) = client.post {
        url {
            appendPathSegments(participantDto.bandId.toString())
            appendPathSegments("part")
            appendPathSegments(participantDto.name)
        }
    }
}


