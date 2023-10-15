package jp.speakbuddy.catfact.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.catfact.data.repository.FactRepository
import jp.speakbuddy.catfact.data.repository.FactRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface DataHiltModule {

    @Binds
    fun bindFactRepository(repository: FactRepositoryImpl): FactRepository
}
