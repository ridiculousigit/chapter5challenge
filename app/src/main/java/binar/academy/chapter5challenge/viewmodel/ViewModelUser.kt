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
    var updateUser: MutableLiveData<ResponseDataUserItem> = MutableLiveData()

    fun callPostUser(username: String, email: String, password: String) {
        RetrofitUser.instance.addUser(DataUser(username, email, password, null, "", null))
            .enqueue(object : Callback<ResponseDataUserItem> {
                override fun onResponse(
                    call: Call<ResponseDataUserItem>,
                    response: Response<ResponseDataUserItem>
                ) {
                    if (response.isSuccessful) {
                        postldUser.postValue(response.body())
                    } else {
                        postldUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataUserItem>, t: Throwable) {
                    postldUser.postValue(null)
                }
            })
    }

    fun callAllUser() {
        RetrofitUser.instance.getAllUser().enqueue(object : Callback<List<ResponseDataUserItem>> {
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

    fun update(id: String, fullName: String, address: String, birthDay: String) {
        RetrofitUser.instance.putUser(
            id,
            DataUser(
                fullname = fullName,
                address = address,
                birthday = birthDay,
                email = null,
                username = null,
                password = null
            )
        )
            .enqueue(object : Callback<ResponseDataUserItem> {
                override fun onResponse(
                    call: Call<ResponseDataUserItem>,
                    response: Response<ResponseDataUserItem>
                ) {
                    if (response.isSuccessful) {
                        updateUser.postValue(response.body())
                    } else {
                        updateUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataUserItem>, t: Throwable) {
                    updateUser.postValue(null)
                }
            })
    }
}