package jp.speakbuddy.catfact.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import jp.speakbuddy.catfact.database.model.FactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FactDao {

    @Query("SELECT * FROM facts ORDER BY id DESC LIMIT 1")
    fun fetchLatest(): Flow<FactEntity?>

    @Insert
    suspend fun add(fact: FactEntity)
}
