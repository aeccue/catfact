package jp.speakbuddy.catfact.database

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.speakbuddy.catfact.database.dao.FactDao
import jp.speakbuddy.catfact.database.model.FactEntity

@Database(
    entities = [FactEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class LocalDatabase : RoomDatabase() {

    abstract fun factDao(): FactDao
}
