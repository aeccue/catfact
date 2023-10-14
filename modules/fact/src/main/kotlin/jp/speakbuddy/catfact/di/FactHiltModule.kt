package jp.speakbuddy.catfact.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.dataStoreFile
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.catfact.fact.data.model.FactResponse
import jp.speakbuddy.catfact.fact.data.model.FactResponseSerializer
import jp.speakbuddy.catfact.fact.data.repository.FactRepository
import jp.speakbuddy.catfact.fact.data.repository.FactRepositoryImpl
import jp.speakbuddy.catfact.fact.data.source.FactDataStore
import jp.speakbuddy.catfact.fact.data.source.FactLocalDataSource
import jp.speakbuddy.catfact.fact.data.source.FactRemoteDataSource
import jp.speakbuddy.catfact.fact.data.source.FactService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataStoreModule {

    companion object {

        @Provides
        @Singleton
        fun provideFactDataStore(
            @ApplicationContext context: Context,
            serializer: Serializer<FactResponse>
        ): DataStore<FactResponse> =
            DataStoreFactory.create(serializer = serializer) {
                context.dataStoreFile("fact.pb")
            }
    }

    @Binds
    fun bindSerializer(serializer: FactResponseSerializer): Serializer<FactResponse>

    @Binds
    fun bindLocalDataSource(source: FactDataStore): FactLocalDataSource

    @Binds
    fun bindRemoteDataSource(source: FactService): FactRemoteDataSource

    @Binds
    fun bindRepository(repository: FactRepositoryImpl): FactRepository
}
