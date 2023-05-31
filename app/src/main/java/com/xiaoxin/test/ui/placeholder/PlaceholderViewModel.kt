package com.xiaoxin.test.ui.placeholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.xiaoxin.test.FakeService

/**
 * @author XiaoXinC
 */
class PlaceHolderViewModel : ViewModel() {

    val flow = Pager(
        //step 1 开启占位符功能：enablePlaceholders = true
        config = PagingConfig(pageSize = 10, enablePlaceholders = true),
        pagingSourceFactory = { PlaceholderPagingSource(FakeService()) }
    )
        .flow
        .cachedIn(viewModelScope)

}