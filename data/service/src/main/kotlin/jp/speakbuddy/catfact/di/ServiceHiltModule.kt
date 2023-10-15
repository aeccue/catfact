package jp.speakbuddy.catfact.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ServiceHiltModule {

    @Singleton
    @Binds
    fun bindCatFactService(service: jp.speakbuddy.catfact.service.CatFactServiceImpl): jp.speakbuddy.catfact.service.CatFactService
}
