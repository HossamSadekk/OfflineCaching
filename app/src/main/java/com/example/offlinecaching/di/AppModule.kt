package com.example.offlinecaching.di

import com.example.offlinecaching.common.Constants
import com.example.offlinecaching.data.remote.CharacterApi
import com.example.offlinecaching.data.repository.CharacterRepositoryImpl
import com.example.offlinecaching.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.*


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): CharacterApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterApi::class.java)


    @Provides
    @Singleton
    fun provideCoinRepository(api: CharacterApi):CharacterRepository =
        CharacterRepositoryImpl(api)
}