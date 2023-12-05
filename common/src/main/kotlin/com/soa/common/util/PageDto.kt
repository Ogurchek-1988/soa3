package com.soa.common.util

open class PageDto<T> (
    val page: Int,
    val size: Int,
    val total: Long,
    val data: List<T>?
)