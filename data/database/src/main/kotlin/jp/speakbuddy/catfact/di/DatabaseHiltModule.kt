package jp.speakbuddy.catfact.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.speakbuddy.catfact.database.LocalDatabase
import jp.speakbuddy.catfact.database.dao.FactDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseHiltModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LocalDatabase =
        Room.databaseBuilder(
            context = context,
            klass = LocalDatabase::class.java,
            name = "local-database"
        ).build()

    @Provides
    fun provideFactDao(database: LocalDatabase): FactDao = database.factDao()
}
