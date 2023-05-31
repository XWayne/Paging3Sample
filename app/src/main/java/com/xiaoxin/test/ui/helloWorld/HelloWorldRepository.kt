package com.xiaoxin.test.ui.helloWorld

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.xiaoxin.test.FakeService
import com.xiaoxin.test.db.Message


class HelloWorldPagingSource(
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
            nextKey = if (nextKey >=100) null else nextKey
        )
    }
}



