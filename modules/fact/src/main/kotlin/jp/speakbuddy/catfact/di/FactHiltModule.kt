package jp.speakbuddy.catfact.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.catfact.fact.data.model.FactResponse
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreModule {

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
}
