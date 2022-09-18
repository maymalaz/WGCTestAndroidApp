package com.example.wgctestandroidapp.domain.common

import com.example.wgctestandroidapp.domain.mappers.NetworkModel


data class HttpResult<T>(val success: Boolean = false,
                         val code : Int= 0,
                         val message: String? = null,
                         val version: String? = null,
                         val data: T? = null): NetworkModel


