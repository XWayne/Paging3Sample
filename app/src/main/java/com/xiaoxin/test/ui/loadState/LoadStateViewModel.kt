package com.xiaoxin.test.ui.loadState


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.xiaoxin.test.FakeService

class LoadStateViewModel : ViewModel() {

    val flow = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { LoadStatePagingSource(FakeService()) }
    )
        .flow
        .cachedIn(viewModelScope)

}


