package com.chichi289.week5.di

import com.chichi289.week5.BuildConfig
import com.chichi289.week5.utils.BASE_URL_PICSUM_PHOTOS
import com.chichi289.week5.utils.BASE_URL_RANDOM_DATA
import com.chichi289.week5.utils.RETROFIT_PICSUM_PHOTOS
import com.chichi289.week5.utils.RETROFIT_RANDOM_DATA
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        )

    @Singleton
    @Provides
    fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Singleton
    @Provides
    @Named(RETROFIT_RANDOM_DATA)
    fun providerRetrofitRandomData(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_RANDOM_DATA)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    @Named(RETROFIT_PICSUM_PHOTOS)
    fun providerRetrofitPicsumPhotos(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_PICSUM_PHOTOS)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}