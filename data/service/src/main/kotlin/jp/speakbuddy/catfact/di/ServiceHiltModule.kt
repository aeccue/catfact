package jp.speakbuddy.catfact.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.catfact.service.CatFactService
import jp.speakbuddy.catfact.service.CatFactServiceImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface ServiceHiltModule {

    @Binds
    fun bindCatFactService(service: CatFactServiceImpl): CatFactService
}
