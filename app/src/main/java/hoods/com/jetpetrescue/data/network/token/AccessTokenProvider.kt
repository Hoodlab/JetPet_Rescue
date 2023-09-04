package hoods.com.jetpetrescue.data.network.token

import hoods.com.jetpetrescue.data.network.models.AccessToken

interface AccessTokenProvider {

    suspend fun fetchAccessToken(): AccessToken?

    fun refreshToken(): AccessToken?

    fun saveToken(token: String?)

    fun token(): String?

    fun lock(): Any

}