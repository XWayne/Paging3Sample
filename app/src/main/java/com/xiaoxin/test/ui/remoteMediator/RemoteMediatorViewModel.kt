package com.xiaoxin.test.ui.remoteMediator


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.xiaoxin.test.FakeService
import com.xiaoxin.test.db.MessageDb

class RemoteMediatorViewModel(application: Application) : AndroidViewModel(application) {

    @OptIn(ExperimentalPagingApi::class)
    val flow = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { MessageDb.get(application.applicationContext).messageDao().getAllMessage() },
        remoteMediator = MyRemoteMediator(FakeService(), MessageDb.get(application.applicationContext))
    )
        .flow
        .cachedIn(viewModelScope)

}


