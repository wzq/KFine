package com.firstlink.duo.model.vo

import com.firstlink.duo.model.Order
import com.firstlink.duo.model.Topic

/**
 * Created by wzq on 16/1/7.
 */
data class TopicData (
        val topic: Topic,
        val list: List<Order>
)