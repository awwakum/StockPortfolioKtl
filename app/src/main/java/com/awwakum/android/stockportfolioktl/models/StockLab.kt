package com.awwakum.android.stockportfolioktl.models

import android.content.Context
import java.util.*

class StockLab private constructor(context: Context?) {

    private lateinit var mStocks: MutableList<Stock>

    init {
        mStocks = arrayListOf()
        for (i in 0..100) {
            var stock = Stock("Stock #" + i, (i % 2 == 0))
            mStocks.add(stock)
        }
    }

    companion object {
//        private var sStockLab: StockLab? = null
            private var sStockLab: StockLab? = null
//        private lateinit var mStocks: List<Stock>

        fun get(context: Context?): StockLab {
            if (sStockLab == null) {
                sStockLab = StockLab(context)
            }
            return sStockLab!!
        }

//        operator fun invoke() {
//            mStocks = arrayListOf()
//            for (int i = 0; i < 100; i++) {
//                Crime crime = new Crime();
//                crime.setTitle("Crime #" + i);
//                crime.setSolved(i % 2 == 0); // Для каждого второго объекта
//                mCrimes.add(crime);
//            }
//        }
    }

    public fun getStocks(): List<Stock> {
        return mStocks
    }

    public fun getStock(uuid: UUID): Stock? {
        for (stock: Stock in mStocks) {
            if(stock.mId.equals(uuid)) {
                return stock
            }
        }
        return null
    }
}
