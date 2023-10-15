package jp.speakbuddy.catfact.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facts")
data class FactEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fact: String,
    val length: Int
)
