package jp.speakbuddy.catfact.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import jp.speakbuddy.catfact.data.model.Fact
import jp.speakbuddy.catfact.room.model.FactEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FactDao {

    @Query("SELECT * FROM facts ORDER BY id DESC LIMIT 1")
    fun fetchLatest(): Flow<FactEntity>

    @Insert
    suspend fun add(fact: Fact)
}
