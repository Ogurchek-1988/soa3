package com.soa.secondservice.util

import io.ktor.client.statement.*

suspend fun toResponse(response: HttpResponse): String =
        response.bodyAsText()