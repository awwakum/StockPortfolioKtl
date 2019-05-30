package com.awwakum.android.stockportfolioktl.models

import java.util.*

data class Stock(var mTitle: String = "", var mBought: Boolean = false) {
    var mId: UUID
    var mDate: Date

    init {
        mId = UUID.randomUUID()
        mDate = Date()
    }
}