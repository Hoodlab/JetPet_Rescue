package hoods.com.jetpetrescue.data.network.interceptors

import android.util.Log
import hoods.com.jetpetrescue.data.network.token.AccessTokenProvider
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AccessInterceptor(
    private val accessTokenProvider: AccessTokenProvider,
) : Interceptor {
    companion object {
        const val TAG = "myApp"
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().signedRequest()
        return chain.proceed(newRequest)
    }

    private fun Request.signedRequest(): Request {
        Log.i(TAG, "signedRequest: ${accessTokenProvider.token()}")
        return newBuilder()
            .addHeader(
                "Authorization",
                "Bearer  ${accessTokenProvider.token()}",
            )
            .build()
    }
}














