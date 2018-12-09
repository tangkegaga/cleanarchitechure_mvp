package au.com.tangke.tram.di.module


import android.app.Application
import au.com.tangke.tram.BuildConfig
import au.com.tangke.tram.data.net.TramApiService
import au.com.tangke.tram.data.net.RestHttpInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class TramAPIModule {

    companion object {
        private const val CONNECT_TIMEOUT = 120L
        private const val READ_TIMEOUT = 120L
        private const val WRITE_TIMEOUT = 120L
        private var BASE_ENDPOINT: String = "http://ws3.tramtracker.com.au"
    }


    @Provides
    @Singleton
    @Named("provideRestHttpInterceptor")
    fun provideInterceptor(application: Application): RestHttpInterceptor {
        val restHttpInterceptor =
                RestHttpInterceptor()
        return restHttpInterceptor
    }

    @Provides
    @Singleton
    @Named("provideRetrofitHttpClient")
    fun provideHttpClient(@Named("provideRestHttpInterceptor") restHttpInterceptor: RestHttpInterceptor): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder.addInterceptor(restHttpInterceptor)

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    @Named("provideRestRetrofit")
    fun provideRetrofit(@Named("provideRetrofitHttpClient") okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(makeGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private fun makeGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Provides
    fun provideRestAPI(@Named("provideRestRetrofit") retrofit: Retrofit): TramApiService =
            retrofit.create(TramApiService::class.java)
}