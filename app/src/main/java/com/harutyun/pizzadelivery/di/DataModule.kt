package com.harutyun.pizzadelivery.di

import com.harutyun.data.BuildConfig
import com.harutyun.data.mapper.PizzaMapper
import com.harutyun.data.remote.PizzasApi
import com.harutyun.data.remote.PizzasApiRemoteDataSource
import com.harutyun.data.remote.PizzasRemoteDataSource
import com.harutyun.data.repository.PizzasRepositoryImpl
import com.harutyun.domain.repository.PizzasRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Singleton
    @Provides
    fun provideGithubReposRemoteDataSource(pizzasApi: PizzasApi): PizzasApiRemoteDataSource {
        return PizzasApiRemoteDataSource(pizzasApi)
    }

    @Singleton
    @Provides
    fun providePizzaMapper(): PizzaMapper {
        return PizzaMapper()
    }

    @Singleton
    @Provides
    fun providePizzasRepository(
        pizzasRemoteDataSource: PizzasRemoteDataSource,
        pizzaMapper: PizzaMapper
    ): PizzasRepository {
        return PizzasRepositoryImpl(
            pizzasRemoteDataSource,
            pizzaMapper
        )
    }

    @Singleton
    @Provides
    fun provideGithubReposService(retrofit: Retrofit): PizzasApi {
        return retrofit.create(PizzasApi::class.java)
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }


}