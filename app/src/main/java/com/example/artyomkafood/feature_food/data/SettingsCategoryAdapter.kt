package com.example.artyomkafood.feature_food.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SettingsCategoryAdapter(
    val date: Long,
    val scheduleId: Int,
    var position: Int = 0,
): Parcelable