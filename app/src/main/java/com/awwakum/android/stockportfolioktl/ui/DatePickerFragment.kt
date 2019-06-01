package com.awwakum.android.stockportfolioktl.ui

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.DatePicker
import java.util.*

class DatePickerFragment : DialogFragment() {

    private lateinit var mDatePicker: DatePicker

    private fun sendResult(resultCode: Int, date: Date) {
        if (targetFragment == null) {
            return
        }
        var intent = Intent()
        intent.putExtra(EXTRA_DATE, date)

        targetFragment!!.onActivityResult(targetRequestCode, resultCode, intent)
    }

    companion object {

        private const val ARG_DATE: String = "date"
        val EXTRA_DATE = "com.awwakum.android.stockportfolioktl.date"

        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle()
            args.putSerializable(ARG_DATE, date)

            val fragment = DatePickerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val date = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        var v = LayoutInflater.from(activity).inflate(com.awwakum.android.stockportfolioktl.R.layout.dialog_date, null)

        mDatePicker = v.findViewById(com.awwakum.android.stockportfolioktl.R.id.dialog_date_picker)
        mDatePicker.init(year, month, day, null)

        return AlertDialog.Builder(activity!!)
            .setView(v)
            .setTitle(com.awwakum.android.stockportfolioktl.R.string.date_picker_title)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val year = mDatePicker.year
                val month = mDatePicker.month
                val day = mDatePicker.dayOfMonth
                val date = GregorianCalendar(year, month, day).time
                sendResult(Activity.RESULT_OK, date)
            }
            .create()
    }
}