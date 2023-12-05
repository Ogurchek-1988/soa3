package com.soa.firstservice.exception

import jakarta.ws.rs.core.Response
import java.lang.Exception

abstract class BaseException(
    override val message: String,
    val httpStatus: Response.Status
): Exception(message)