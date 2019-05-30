package com.awwakum.android.stockportfolioktl

import android.support.v4.app.Fragment
import com.awwakum.android.stockportfolioktl.ui.StockListFragment

class StockListActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment {
        return StockListFragment()
    }
}