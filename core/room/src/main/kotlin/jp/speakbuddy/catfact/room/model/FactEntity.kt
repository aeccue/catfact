package jp.speakbuddy.catfact.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facts")
internal data class FactEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val fact: String,
    val length: Int
)
