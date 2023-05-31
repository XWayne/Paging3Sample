package com.xiaoxin.test

import com.xiaoxin.test.db.Message
import kotlinx.coroutines.delay

/**
 * @author XiaoXinC
 */
class FakeService {

    suspend fun fetchData(range:IntRange):List<Message>{
        delay(500L)
        return range.map {
            Message(
                id = it,
                content = "Hello World!!"
            )
        }
    }
}