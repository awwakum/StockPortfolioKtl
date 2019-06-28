package com.awwakum.android.stockportfolioktl.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import com.awwakum.android.stockportfolioktl.R
import com.awwakum.android.stockportfolioktl.models.Stock
import com.awwakum.android.stockportfolioktl.models.StockLab
import java.util.*

class StockFragment : Fragment() {

    companion object {
        private final val ARG_STOCK_ID: String = "stock_id"
        private final val DIALOG_DATE: String = "DialogDate"
        private const val REQUEST_DATE = 0

        fun newInstance(stockId: UUID) : StockFragment {
            var args = Bundle()
            args.putSerializable(ARG_STOCK_ID, stockId)
            var fragment = StockFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mStock: Stock
    private lateinit var mTitleField: EditText
    private lateinit var mDateButton: Button
    private lateinit var mBoughtCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var stockId = arguments?.getSerializable(ARG_STOCK_ID) as UUID
        mStock = StockLab.get(activity).getStock(stockId)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v = inflater.inflate(R.layout.fragment_stock, container, false)

        mTitleField = v.findViewById(R.id.stock_title) as EditText
        mTitleField.setText(mStock.mTitle)
        mTitleField.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mStock.mTitle = s.toString()
            }
        })

        mDateButton = v.findViewById(R.id.stock_date) as Button
        updateDate()
        mDateButton.setOnClickListener {
            val manager = fragmentManager
            var dialog = DatePickerFragment.newInstance(mStock.mDate)
            dialog.setTargetFragment(this@StockFragment, REQUEST_DATE)
            dialog.show(manager, DIALOG_DATE)
        }

        mBoughtCheckBox = v.findViewById(R.id.stock_bought) as CheckBox
//        mBoughtCheckBox.setOnCheckedChangeListener buttonView, isChecked -> mStock.mBought = isChecked }
        mBoughtCheckBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener{buttonView, isChecked -> mStock.mBought = isChecked })

        return v
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_DATE) {
            var date = data?.getSerializableExtra(DatePickerFragment.EXTRA_DATE) as Date
            mStock.mDate = date
            updateDate()
        }
    }

    private fun updateDate() {
        mDateButton.text = mStock.mDate.toString()
    }
}