package com.example.willywonka.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.willywonka.gson.OompaLoompaWorkers
import com.example.willywonka.gson.Worker
import com.example.willywonka.retrofit.APIServiceInterface
import com.example.willywonka.retrofit.RetrofitConf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse

class OompaLoompaWorkersViewModel : ViewModel() {

    private var retrofit: RetrofitConf? = null
    private var api: APIServiceInterface? = null
    private var _itemMutableList: MutableLiveData<List<Worker>> = MutableLiveData()
    var itemMutableList: LiveData<List<Worker>> = _itemMutableList
    private var _workersServiceError: MutableLiveData<Boolean> = MutableLiveData()
    var workersServiceError: LiveData<Boolean> = _workersServiceError
    private var _pbVisibility: MutableLiveData<Boolean> = MutableLiveData()
    var pbVisibility: LiveData<Boolean> = _pbVisibility


    fun getWorkers() {
        _pbVisibility.postValue(true)
        retrofit = RetrofitConf()
        api = retrofit?.getApiService()
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<OompaLoompaWorkers>? = api?.getWorkers()?.awaitResponse()
            if (response?.isSuccessful == true) {
                _pbVisibility.postValue(false)
                _itemMutableList.postValue(response.body()?.workersData)
            } else {
                _pbVisibility.postValue(false)
                _workersServiceError.postValue(true)
            }
        }
    }
}