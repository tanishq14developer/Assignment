package com.tanishq.assignment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application):AndroidViewModel(application) {

    enum class RadioType {
        MALE,
        FEMALE,
        OTHERS,
    }

    var radioType = MutableLiveData(RadioType.MALE)
    val radioItem :LiveData<RadioType>
    get() = radioType




}
