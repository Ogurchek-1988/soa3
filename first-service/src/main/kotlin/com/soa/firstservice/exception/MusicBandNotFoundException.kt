package com.soa.firstservice.exception

import jakarta.ws.rs.core.Response

class MusicBandNotFoundException(message: String = "MusicBand not found"): BaseException(message, Response.Status.NOT_FOUND)