package com.xiaoxin.test.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Message(
    @PrimaryKey
    val id: Int,
    val content: String,
)

