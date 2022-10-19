package com.tanishq.assignment.utils

import androidx.databinding.InverseMethod
import com.tanishq.assignment.R
import com.tanishq.assignment.viewModel.MainViewModel

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