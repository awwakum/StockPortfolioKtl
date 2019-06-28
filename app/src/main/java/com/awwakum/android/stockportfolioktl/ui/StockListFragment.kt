package com.awwakum.android.stockportfolioktl.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import com.awwakum.android.stockportfolioktl.R
import com.awwakum.android.stockportfolioktl.StockPagerActivity
import com.awwakum.android.stockportfolioktl.models.Stock
import com.awwakum.android.stockportfolioktl.models.StockLab

class StockListFragment : Fragment() {

    private lateinit var mStockRecyclerView: RecyclerView
    private var mAdapter: StockAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var view = inflater.inflate(R.layout.fragment_stock_list, container, false)
        mStockRecyclerView = view.findViewById(R.id.stock_recycler_view)
        mStockRecyclerView.layoutManager = LinearLayoutManager(activity)
        updateUI()
        return view
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fragment_stock_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.new_stock -> {
                var stock = Stock()
                StockLab.get(activity).addStock(stock)
                var intent = StockPagerActivity.newIntent(activity, stock.mId)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateUI() {
        var stockLab = StockLab.get(activity?.applicationContext)
        var stocks = stockLab.getStocks()

        if (mAdapter == null) {
            mAdapter = StockAdapter(stocks)
            mStockRecyclerView.adapter = mAdapter
        } else {
            println("mAdapter is not null")
            // TODO notifyItemChanged(int)
            mAdapter!!.notifyDataSetChanged()
        }
    }

    private inner class StockHolder constructor(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_stock, parent, false)), View.OnClickListener {

        private var mStock: Stock? = null

        private val mTitleTextView: TextView
        private val mDateTextView: TextView

        init {
            itemView.setOnClickListener(this)

            mTitleTextView = itemView.findViewById(R.id.stock_title)
            mDateTextView = itemView.findViewById(R.id.stock_date)
        }

        fun bind(stock: Stock) {
            mStock = stock
            mTitleTextView.text = mStock!!.mTitle
            mDateTextView.text = mStock!!.mDate.toString()
        }

        override fun onClick(v: View?) {
//            val intent = Intent(activity, StockActivity::class.java)
            var intent = StockPagerActivity.newIntent(context, mStock!!.mId)
            startActivity(intent)
        }
    }

    private inner class StockAdapter constructor(private val mStocks: List<Stock>): RecyclerView.Adapter<StockHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
            val layoutInflater = LayoutInflater.from(activity)
            return StockHolder(layoutInflater, parent)
        }

        override fun getItemCount(): Int {
            return mStocks.size
        }

        override fun onBindViewHolder(holder: StockHolder, position: Int) {
            val stock = mStocks[position]
            holder.bind(stock)
        }

    }
}