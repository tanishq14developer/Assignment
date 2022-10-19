package com.tanishq.assignment.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tanishq.assignment.model.ApiBody
import com.tanishq.assignment.model.ApiResponse
import com.tanishq.assignment.network.ApiClient.getRetrofitService
import com.tanishq.assignment.network.ApiInterface
import com.tanishq.assignment.network.ResultWrappers
import kotlinx.coroutines.launch

class MainViewModel(application: Application):AndroidViewModel(application) {

    enum class RadioType {
        MALE,
        FEMALE,
        OTHERS,
    }

    var radioType = MutableLiveData(RadioType.MALE)
    val radioItem :LiveData<RadioType>
    get() = radioType

    var email= MutableLiveData<String>()
    val mutablePinCodeLiveData = MutableLiveData<ResultWrappers<ApiResponse>>()
    val livePinCodeData: LiveData<ResultWrappers<ApiResponse>>
        get() = mutablePinCodeLiveData

     fun getResponse(apiBody: ApiBody) {
        viewModelScope.launch {

            val service = getRetrofitService(ApiInterface::class.java)
            val result = service.postDetails(apiBody)
            if (result.isSuccessful && result.body() != null) {
                mutablePinCodeLiveData.value = ResultWrappers.Success(result.body()!!)
                Log.d("pinCodeResponse", "getPinCode: ${result.body()}")
            } else if (result.errorBody() != null) {
                mutablePinCodeLiveData.value = ResultWrappers.Error("Something Went Wrong")
            } else {
                mutablePinCodeLiveData.value = ResultWrappers.Error("Something Went Wrong")
            }

        }
    }





}
