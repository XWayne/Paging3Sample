package com.xiaoxin.test.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * @author XiaoXinC
 */
@Database(
    entities = [Message::class], version = 1, exportSchema = false
)
abstract class MessageDb : RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object {

        private var instance: MessageDb? = null

        @Synchronized
        fun get(context: Context): MessageDb {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MessageDb::class.java, "MessageDatabase"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                        }
                    }).build()
            }
            return instance!!
        }
    }
}
