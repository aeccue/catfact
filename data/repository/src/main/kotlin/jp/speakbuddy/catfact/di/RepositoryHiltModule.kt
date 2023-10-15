package jp.speakbuddy.catfact.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jp.speakbuddy.catfact.repository.FactRepository
import jp.speakbuddy.catfact.repository.FactRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
internal interface RepositoryHiltModule {

    @Binds
    fun bindFactRepository(repository: FactRepositoryImpl): FactRepository
}
