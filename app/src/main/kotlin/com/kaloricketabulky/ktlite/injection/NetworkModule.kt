package com.kaloricketabulky.ktlite.injection

import com.kaloricketabulky.ktlite.BuildConfig
import com.kaloricketabulky.ktlite.data.remote.KalorickeTabulkyApi
import com.kaloricketabulky.ktlite.data.repository.KTLiteRepositoryImpl
import com.kaloricketabulky.ktlite.domain.repository.KTLiteRepository
import com.kaloricketabulky.ktlite.tools.Constants
import com.kaloricketabulky.ktlite.tools.Constants.Api.TIMEOUT_SECONDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jaxb.JaxbConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor { message ->
            Timber.tag("OkHttp").d(message)
        }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain -> authInterceptor(chain) }
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun providesKalorickeTabulkyApi(
        client: OkHttpClient
    ) : KalorickeTabulkyApi {
        return Retrofit.Builder()
            .baseUrl(Constants.Api.BASE_URL)
            .client(client)
            .addConverterFactory(JaxbConverterFactory.create())
            .build()
            .create(KalorickeTabulkyApi::class.java)
    }

    @Provides
    @Singleton
    fun providesKTLiteRespository(api: KalorickeTabulkyApi) : KTLiteRepository =
        KTLiteRepositoryImpl(api)

    private fun authInterceptor(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl = originalRequest.url.newBuilder()
            .addQueryParameter("pid", BuildConfig.AUTH_KEY)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}

