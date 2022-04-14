package com.between_freedom_and_space.mono_backend.auth.service

import com.between_freedom_and_space.mono_backend.auth.components.models.TokenVerifyResult

interface TokenAuthService {

    fun refreshAccessToken(refreshToken: String): String

    fun verifyAccessToken(token: String): TokenVerifyResult

    fun verifyRefreshToken(token: String): TokenVerifyResult
}