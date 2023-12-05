package com.soa.firstserviceejb.util

import FilteringOperation

data class Filter(
    val fieldName: String? = null,
    val nestedName: String? = null,
    val filteringOperation: FilteringOperation? = null,
    val fieldValue: String? = null
)
