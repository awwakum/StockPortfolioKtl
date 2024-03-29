//package com.bignerdranch.android.criminalintent
//
//import android.app.Activity
//import android.content.Intent
//import android.os.Bundle
//import android.support.v4.app.Fragment
//import android.support.v4.app.FragmentManager
//import android.text.Editable
//import android.text.TextWatcher
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.CheckBox
//import android.widget.CompoundButton
//import android.widget.EditText
//
//import java.util.Date
//import java.util.UUID
//
//import android.widget.CompoundButton.*
//
//class CrimeFragment : Fragment() {
//
//    private var mCrime: Crime? = null
//    private var mTitleField: EditText? = null
//    private var mDateButton: Button? = null
//    private var mSolvedCheckbox: CheckBox? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val crimeId = arguments!!.getSerializable(ARG_CRIME_ID) as UUID
//        mCrime = CrimeLab.get(activity).getCrime(crimeId)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val v = inflater.inflate(R.layout.fragment_crime, container, false)
//
//        mTitleField = v.findViewById(R.id.crime_title)
//        mTitleField!!.setText(mCrime!!.getTitle())
//        mTitleField!!.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                mCrime!!.setTitle(s.toString())
//            }
//
//            override fun afterTextChanged(s: Editable) {
//
//            }
//        })
//
//        mDateButton = v.findViewById(R.id.crime_date)
//        updateDate()
//        mDateButton!!.setOnClickListener {
//            val manager = fragmentManager
//            val dialog = DatePickerFragment
//                .newInstance(mCrime!!.getDate())
//            dialog.setTargetFragment(this@CrimeFragment, REQUEST_DATE)
//            dialog.show(manager, DIALOG_DATE)
//        }
//
//        mSolvedCheckbox = v.findViewById(R.id.crime_solved)
//        mSolvedCheckbox!!.isChecked = mCrime!!.isSolved()
//        mSolvedCheckbox!!.setOnCheckedChangeListener { buttonView, isChecked -> mCrime!!.setSolved(isChecked) }
//
//        return v
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (resultCode != Activity.RESULT_OK) {
//            return
//        }
//
//        if (requestCode == REQUEST_DATE) {
//            val date = data!!
//                .getSerializableExtra(DatePickerFragment.EXTRA_DATE) as Date
//            mCrime!!.setDate(date)
//            updateDate()
//        }
//    }
//
//    private fun updateDate() {
//        mDateButton!!.setText(mCrime!!.getDate().toString())
//    }
//
//    companion object {
//
//        private val ARG_CRIME_ID = "crime_id"
//        private val DIALOG_DATE = "DialogDate"
//
//        private val REQUEST_DATE = 0
//
//        fun newInstance(crimeId: UUID): CrimeFragment {
//            val args = Bundle()
//            args.putSerializable(ARG_CRIME_ID, crimeId)
//
//            val fragment = CrimeFragment()
//            fragment.arguments = args
//            return fragment
//        }
//    }
//}
