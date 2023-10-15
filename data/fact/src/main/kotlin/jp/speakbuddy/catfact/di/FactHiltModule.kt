package jp.speakbuddy.catfact.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.catfact.fact.data.repository.FactRepository
import jp.speakbuddy.catfact.fact.data.repository.FactRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface FactHiltModule {

    @Binds
    fun bindRepository(repository: FactRepositoryImpl): FactRepository
}
