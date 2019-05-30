package com.awwakum.android.stockportfolioktl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.awwakum.android.stockportfolioktl.models.Stock
import com.awwakum.android.stockportfolioktl.models.StockLab
import com.awwakum.android.stockportfolioktl.ui.StockFragment
import java.util.*

class StockPagerActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_STOCK_ID = "com.awwakum.android.stockportfolioktl.stock_id"

        fun newIntent(packageContext: Context?, stockId: UUID) : Intent {
            val intent = Intent(packageContext, StockPagerActivity::class.java)
            intent.putExtra(EXTRA_STOCK_ID, stockId)
            return intent
        }
    }

    lateinit var mViewPager: ViewPager
    lateinit var mStocks: List<Stock>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_pager)

        var stockId = intent.getSerializableExtra(EXTRA_STOCK_ID) as UUID

        mViewPager = findViewById(R.id.stock_view_pager)
        mStocks = StockLab.get(this).getStocks()

        var fragmentManager = supportFragmentManager
        // FragmentPagerAdapter - если количество элементов небольшое и ограничено
        mViewPager.adapter = object : FragmentStatePagerAdapter(fragmentManager) {
            override fun getItem(position: Int): Fragment {
                var stock = mStocks[position]
                return StockFragment.newInstance(stock.mId)
            }

            override fun getCount(): Int {
                return mStocks.size
            }
        }

        for ( i in mStocks.indices) {
            if (mStocks[i].mId.equals(stockId)) {
                mViewPager.currentItem = i
                break
            }
        }
    }
}