package io.github.kabirnayeem99.friends.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.kabirnayeem99.friends.BuildConfig
import io.github.kabirnayeem99.friends.data.sources.RemoteDataSource
import io.github.kabirnayeem99.friends.utils.constants.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {

        // if the app is in debug mode, show the logs
        // else don't show any log
        val levelType: HttpLoggingInterceptor.Level =
            if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE


        return HttpLoggingInterceptor()
            .apply {
                level = levelType
            }
    }


    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): RemoteDataSource = retrofit.create(RemoteDataSource::class.java)

}