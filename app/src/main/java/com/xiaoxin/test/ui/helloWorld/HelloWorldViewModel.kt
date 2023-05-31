package com.xiaoxin.test.ui.helloWorld


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.xiaoxin.test.FakeService

class HelloWorldViewModel : ViewModel() {

    val flow = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { HelloWorldPagingSource(FakeService()) }
    )
        .flow
        .cachedIn(viewModelScope)

}


