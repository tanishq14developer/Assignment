package com.tanishq.assignment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tanishq.assignment.adapter.CartAdapterTwo
import com.tanishq.assignment.databinding.ActivityMainBinding
import com.tanishq.assignment.model.ApiBody
import com.tanishq.assignment.utils.AppConstants
import com.tanishq.assignment.utils.validateEmailAddress
import com.tanishq.assignment.viewModel.MainViewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var datePickerDialog: DatePickerDialog
    lateinit var apiBody: ApiBody
    lateinit var emailAddress: String
    var gender: String? = null
    var date: String? = null
    val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH);
        val year: Int = calendar.get(Calendar.YEAR);
        val month: Int = calendar.get(Calendar.MONTH);


        binding.viewPager.adapter = CartAdapterTwo(applicationContext, AppConstants.imageUrlList)
        binding.tabIndicator.setViewPager(binding.viewPager)
        binding.apply {
            emailAddress = email.text.toString()
            mainViewModel = viewModel
            lifecycleOwner = this@MainActivity
            monthText.setOnClickListener {
                datePickerDialog = DatePickerDialog(
                    this@MainActivity,
                    { view, year, month, dayOfMonth -> // adding the selected date in the edittext
                        monthText.setText(dayOfMonth.toString() + "/" + (month + 1) + "/" + year)
                        date =   "${year}-${month + 1}-${dayOfMonth}"
                    },
                    year,
                    month,
                    day
                )

                // set maximum date to be selected as today

                // set maximum date to be selected as today
                datePickerDialog.getDatePicker().maxDate = calendar.timeInMillis

                // show the dialog

                // show the dialog
                datePickerDialog.show()
            }

            submit.setOnClickListener {
                  if (date.isNullOrEmpty()) {
                      Toast.makeText(this@MainActivity, "Please Select Date", Toast.LENGTH_SHORT).show()


                  }else if (email.text.toString().isNullOrEmpty()) {
                      email.setError("Please Enter Email")


                  }else if(email.text.toString().isNullOrEmpty()) {
                      val b = validateEmailAddress(email.text.toString())
                      if (!b) {
                          email.setError("Please Enter Valid Email")
                      }else{
                          emailAddress = email.getText().toString()
                      }
                  } else {
                      emailAddress = email.getText().toString()
                      apiBody = ApiBody("tanishq.happy@gmail.com", date.toString(), emailAddress, gender.toString())
                      viewModel.getResponse(apiBody)


                }

            }
            viewModel.mutablePinCodeLiveData.observe(this@MainActivity, ){
                Log.e("Respooooonnnnnssseeeee", "onCreate: ${it.data}")
                Toast.makeText(this@MainActivity, "${it.data.toString()}", Toast.LENGTH_SHORT).show()
            }
            viewModel.radioItem.observe(this@MainActivity) {
                Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
                gender = it.toString()
            }




        }

    }
}