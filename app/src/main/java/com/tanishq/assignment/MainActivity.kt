package com.tanishq.assignment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tanishq.assignment.adapter.CartAdapterTwo
import com.tanishq.assignment.databinding.ActivityMainBinding
import com.tanishq.assignment.utils.AppConstants
import com.tanishq.assignment.viewModel.MainViewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var datePickerDialog :DatePickerDialog
    val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val calendar:Calendar = Calendar.getInstance()
        val day: Int  = calendar.get(Calendar.DAY_OF_MONTH);
        val   year:Int = calendar.get(Calendar.YEAR);
        val month:Int = calendar.get(Calendar.MONTH);


        binding.viewPager.adapter = CartAdapterTwo(applicationContext,AppConstants.imageUrlList)
        binding.tabIndicator.setViewPager(binding.viewPager)
        binding.apply {
            mainViewModel = viewModel
            lifecycleOwner = this@MainActivity
            monthText.setOnClickListener {
                datePickerDialog = DatePickerDialog(this@MainActivity,
                    { view, year, month, dayOfMonth -> // adding the selected date in the edittext
                        monthText.setText(dayOfMonth.toString() + "/" + (month + 1) + "/" + year)
                    }, year, month, day
                )

                // set maximum date to be selected as today

                // set maximum date to be selected as today
                datePickerDialog.getDatePicker().maxDate = calendar.timeInMillis

                // show the dialog

                // show the dialog
                datePickerDialog.show()
            }

        }

        viewModel.radioItem.observe(this) {
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
            Log.e("Item", "onCreate: ${it}")
        }
    }
}