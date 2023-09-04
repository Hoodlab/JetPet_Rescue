package hoods.com.jetpetrescue.data.network.token

import hoods.com.jetpetrescue.Graph
import hoods.com.jetpetrescue.data.local.StoragePref
import hoods.com.jetpetrescue.data.network.models.AccessToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccessTokenProviderImpl(
    private val storagePref: StoragePref,
) : AccessTokenProvider {
    override suspend fun fetchAccessToken(): AccessToken = withContext(Dispatchers.IO) {
        val accessToken = Graph.apiService.getAuthToken()
        saveToken(accessToken.accessToken)
        accessToken
    }

    override fun refreshToken(): AccessToken? {
        return null
    }

    override fun saveToken(token: String?) {
        storagePref.saveToken(token)
    }

    override fun token(): String? {
        return storagePref.getToken()
    }

    override fun lock(): Any {
        return this
    }
}