package com.xiaoxin.test.ui.placeholder

import android.util.Log
import androidx.annotation.RestrictTo
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import com.xiaoxin.test.FakeService
import com.xiaoxin.test.db.Message
import kotlinx.coroutines.delay
import java.util.concurrent.atomic.AtomicBoolean



class PlaceholderPagingSource(
    private val service: FakeService
) : PagingSource<Int, Message>() {


    override fun getRefreshKey(state: PagingState<Int, Message>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Message> {
        val key = params.key ?: 0

        val nextKey = key + params.loadSize
        val newData = service.fetchData(key.until(nextKey))

        return LoadResult.Page(
            data = newData,
            prevKey = null,
            nextKey = if (nextKey >=100) null else nextKey,
            //step 2 插入10个占位符
            itemsAfter = 10
        )
    }
}



