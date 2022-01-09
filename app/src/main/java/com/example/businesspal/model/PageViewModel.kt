package com.example.businesspal.model


import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.businesspal.retrofit.RetrofitService
import com.example.businesspal.room.BusinessDatabaseBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainViewModel() : ViewModel() {

    val TAG = "View Model Error"
    val movieList = MutableLiveData<List<BusinessDataModel>>()
    val errorMessage = MutableLiveData<String>()


    fun getAllMovies(context: Context) {

        val retrofitService = RetrofitService.getInstance()

        retrofitService.getAllData().enqueue(object : Callback<List<BusinessDataModel>> {
            override fun onResponse(
                call: Call<List<BusinessDataModel>>,
                response: Response<List<BusinessDataModel>>
            ) {


                movieList.postValue(response.body()?.reversed())
                Thread {
                    val db = BusinessDatabaseBuilder.getInstance(context = context)
                    db?.userDao()?.deleteAll()
                    db?.userDao()?.insertAll(response.body()?.reversed())
                }.start()
            }

            override fun onFailure(call: Call<List<BusinessDataModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
                Log.d(TAG, "onFailure: ${t.message}")

                Thread {
                    val db = BusinessDatabaseBuilder.getInstance(context = context)
                    Log.d(
                        TAG, "onDataRetrieve: ${
                            db?.userDao()?.getAll()
                        }"
                    )
                    movieList.postValue(
                        db?.userDao()?.getAll()
                    )
                }.start()

            }
        })
    }
}