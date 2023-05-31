package com.xiaoxin.test.ui.insertSeparator


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import com.xiaoxin.test.FakeService
import com.xiaoxin.test.db.Message
import kotlinx.coroutines.flow.map

class InsertSeparatorViewModel : ViewModel() {

    val flow = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { InsertSeparatorPagingSource(FakeService()) }
    )
        .flow
        .map { pagingData ->
            //step 2 插入header，footer，常规分割符
            pagingData.insertSeparators<Message, Any> { before, after ->
                when {
                    //前面item为null，代表插入的是header
                    before == null -> SeparatorModel("我是header，ID:0->9")


                    //后面item为null，代表插入的是footer
                    after == null -> SeparatorModel("我是Footer，Goodbye!!!")

                    //每隔十个插入一个分隔符
                    before.id % 10 != 0 && after.id % 10 == 0 -> SeparatorModel("ID:${after.id}->${after.id + 9}")
                    else -> null
                }
            }


        }.cachedIn(viewModelScope)
}


