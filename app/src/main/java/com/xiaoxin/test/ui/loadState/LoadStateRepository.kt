package com.xiaoxin.test.ui.loadState

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.xiaoxin.test.FakeService
import com.xiaoxin.test.db.Message
import kotlinx.coroutines.delay
import java.lang.IllegalStateException

private var emitError = true
private var emitLoadMoreError = true

class LoadStatePagingSource(
    private val service: FakeService
) : PagingSource<Int, Message>() {


    override fun getRefreshKey(state: PagingState<Int, Message>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Message> {
        val key = params.key ?: 0

        // step1 第一次请求返回异常
        if (key == 0 && emitError){
            delay(1000)
            emitError = false
            return LoadResult.Error(IllegalStateException("错啦～～"))
        }

        //step6 第一次 loadMore 返回异常
        if (key != 0 && emitLoadMoreError){
            delay(1000)
            emitLoadMoreError = false
            return LoadResult.Error(IllegalStateException("错啦～～"))
        }

        val nextKey = key + params.loadSize
        val newData = service.fetchData(key.until(nextKey))

        return LoadResult.Page(
            data = newData,
            prevKey = null,
            nextKey = if (nextKey >=100) null else nextKey
        )
    }
}



