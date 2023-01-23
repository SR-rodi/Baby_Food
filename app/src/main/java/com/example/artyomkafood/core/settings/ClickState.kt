package com.example.artyomkafood.core.settings

import com.example.artyomkafood.core.database.dao.ScheduleMeal

enum class ClickState(var meal: ScheduleMeal?=null, var index:Int?=null) {
    ADD_BUTTON,SWIPE,CLICK_ITEM
}

