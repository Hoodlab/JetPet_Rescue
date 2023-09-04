package hoods.com.jetpetrescue

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import hoods.com.jetpetrescue.data.local.StoragePref

import hoods.com.jetpetrescue.data.network.K
import hoods.com.jetpetrescue.data.network.interceptors.AccessInterceptor
import hoods.com.jetpetrescue.data.network.interceptors.AccessTokenAuthorization
import hoods.com.jetpetrescue.data.network.mappers.PetApiMapperImpl
import hoods.com.jetpetrescue.data.network.retrofit.PetFinderApiService
import hoods.com.jetpetrescue.data.network.token.AccessTokenProvider
import hoods.com.jetpetrescue.data.network.token.AccessTokenProviderImpl
import hoods.com.jetpetrescue.data.repository.PetRepositoryImpl
import hoods.com.jetpetrescue.domain.repository.PetRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

object Graph {
    lateinit var tokenStoragePref: StoragePref
    lateinit var apiService: PetFinderApiService
    lateinit var accessTokenProvider: AccessTokenProvider
    lateinit var petRepository: PetRepository
    val apiMapper = PetApiMapperImpl()
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AccessInterceptor(accessTokenProvider))
            .authenticator(AccessTokenAuthorization(accessTokenProvider))
            .build()
    }

    fun provide(context: Context) {
        tokenStoragePref = StoragePref(context)
        accessTokenProvider = AccessTokenProviderImpl(tokenStoragePref)
        apiService = createPetFinderApiService()
        petRepository = PetRepositoryImpl(apiService, apiMapper)
    }

    private fun createPetFinderApiService(): PetFinderApiService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(K.BASE_URL)
            .addConverterFactory(
                json
                    .asConverterFactory(contentType)
            )
            .build()
            .create(PetFinderApiService::class.java)
    }


}