package binar.academy.chapter5challenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import binar.academy.chapter5challenge.model.DataUser
import binar.academy.chapter5challenge.model.ResponseDataUserItem
import binar.academy.chapter5challenge.network.RetrofitUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser : ViewModel() {

    var ldUser: MutableLiveData<List<ResponseDataUserItem>> = MutableLiveData()
    var postldUser: MutableLiveData<ResponseDataUserItem> = MutableLiveData()

    fun getldUser(): MutableLiveData<List<ResponseDataUserItem>> {
        return ldUser
    }

    fun addldUser(): MutableLiveData<ResponseDataUserItem> {
        return postldUser
    }

    fun callPostUser(username: String, email: String, password: String) {
        RetrofitUser.instance.addUser(DataUser(username, email, password))
            .enqueue(object :Callback<List<ResponseDataUserItem>> {
                override fun onResponse(
                    call: Call<List<ResponseDataUserItem>>,
                    response: Response<List<ResponseDataUserItem>>
                ) {
                    if (response.isSuccessful) {
                        ldUser.postValue(response.body())
                    } else {
                        ldUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {
                    ldUser.postValue(null)
                }
            })
    }

    fun callAllUser() {
        RetrofitUser.instance.getAllUser().enqueue(object :Callback<List<ResponseDataUserItem>> {
            override fun onResponse(
                call: Call<List<ResponseDataUserItem>>,
                response: Response<List<ResponseDataUserItem>>
            ) {
                if (response.isSuccessful) {
                    ldUser.postValue(response.body())
                } else {
                    ldUser.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {
                ldUser.postValue(null)
            }
        })
    }

    fun requestLoginUser(id: Int, username: String, email: String, password: String) {
        RetrofitUser.instance.putUser(id, DataUser(username, email, password))
            .enqueue(object : Callback<List<ResponseDataUserItem>> {
                override fun onResponse(
                    call: Call<List<ResponseDataUserItem>>,
                    response: Response<List<ResponseDataUserItem>>
                ) {
                    if (response.isSuccessful) {
                        ldUser.postValue(response.body())
                    } else {
                        ldUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {
                    ldUser.postValue(null)
                }
            })
    }
}