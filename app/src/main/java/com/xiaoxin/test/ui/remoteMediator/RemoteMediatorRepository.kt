package com.xiaoxin.test.ui.remoteMediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.xiaoxin.test.FakeService
import com.xiaoxin.test.db.Message
import com.xiaoxin.test.db.MessageDb
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class MyRemoteMediator(
    private val service: FakeService,
    private val database:MessageDb
):RemoteMediator<Int,Message>(){

//    override suspend fun initialize(): InitializeAction {
//        return InitializeAction.SKIP_INITIAL_REFRESH
//    }
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Message>
    ): MediatorResult {
        Log.d("paging3", "Mediator:$loadType")

        val key = when(loadType){
            LoadType.REFRESH -> {

                getKeyClosestToCurrentPosition(state) ?: 0
            }

            LoadType.APPEND -> {
                getKeyForLastItem(state) ?: return MediatorResult.Success(false)
            }

            LoadType.PREPEND -> {
                return MediatorResult.Success(true)
            }
        }


        if (key >= 100) return MediatorResult.Success(true)

        return try {
            val newData = service.fetchData(key.until(key + 10))

            database.messageDao().insert(newData)
//            database.withTransaction {
//
//            }
            MediatorResult.Success(endOfPaginationReached = false)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        }

    }

    private fun getKeyClosestToCurrentPosition(state: PagingState<Int, Message>):Int?{
        return state.anchorPosition?.let {anchorPosition ->
            state.closestItemToPosition(anchorPosition)?.id?.plus(1)
        }
    }

    private  fun getKeyForLastItem(state: PagingState<Int, Message>): Int? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { msg ->
                msg.id + 1
            }
    }
}

//class RemoteMediatorPagingSource(
//    private val service: FakeService
//) : PagingSource<Int, Message>() {
//
//
//    override fun getRefreshKey(state: PagingState<Int, Message>): Int? {
//        return null
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Message> {
//        val key = params.key ?: 0
//
//        val nextKey = key + params.loadSize
//        val newData = service.fetchData(key.until(nextKey))
//
//        return LoadResult.Page(
//            data = newData,
//            prevKey = null,
//            nextKey = if (nextKey >=100) null else nextKey
//        )
//    }
//}



