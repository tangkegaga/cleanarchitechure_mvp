package au.com.tangke.tram.data.net

import okhttp3.Interceptor
import okhttp3.Response



class RestHttpInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain!!.request().newBuilder()
                .build()
        return chain.proceed(request)
    }
}