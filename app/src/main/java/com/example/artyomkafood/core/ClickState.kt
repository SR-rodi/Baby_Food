package com.example.artyomkafood.core

import com.example.artyomkafood.core.database.dao.ScheduleMeal

enum class ClickState(var meal: ScheduleMeal?=null) {
    ADD_BUTTON,SWIPE,CLICK_ITEM
}

