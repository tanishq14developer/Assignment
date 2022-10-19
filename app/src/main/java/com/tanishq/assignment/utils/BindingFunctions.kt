package com.tanishq.assignment.utils

import android.app.Activity
import androidx.databinding.InverseMethod
import com.tanishq.assignment.R
import com.tanishq.assignment.viewModel.MainViewModel
import java.util.regex.Pattern

@InverseMethod("buttonIdToType")
fun typeToButtonId(radioType: MainViewModel.RadioType): Int {
    var selectedButtonId = -1

    radioType?.run {
        selectedButtonId = when (this) {
            MainViewModel.RadioType.MALE -> R.id.male
            MainViewModel.RadioType.FEMALE -> R.id.female
            MainViewModel.RadioType.OTHERS -> R.id.others
        }
    }

    return selectedButtonId
}

fun buttonIdToType(selectedButtonId: Int): MainViewModel.RadioType? {
    var type: MainViewModel.RadioType? = null
    when (selectedButtonId) {
        R.id.male -> {
            type = MainViewModel.RadioType.MALE
        }
        R.id.female -> {
            type = MainViewModel.RadioType.FEMALE
        }
        R.id.others -> {
            type = MainViewModel.RadioType.OTHERS
        }
    }
    return type
}

fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
fun validateEmailAddress(emailAddress: String): Boolean {
    val expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val inputStr: CharSequence = emailAddress
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(inputStr)
    return matcher.matches()
}