package com.xiaoxin.test.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @author XiaoXinC
 */
@Dao
interface MessageDao {

    //ORDER BY id ASC
    @Query("SELECT * FROM Message")
    fun getAllMessage(): PagingSource<Int, Message>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(msgs: List<Message>)




}

