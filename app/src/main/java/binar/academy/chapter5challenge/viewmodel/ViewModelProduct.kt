package binar.academy.chapter5challenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import binar.academy.chapter5challenge.model.DataProduct
import binar.academy.chapter5challenge.model.ResponseDataProductItem
import binar.academy.chapter5challenge.network.RetrofitProduct
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelProduct: ViewModel() {

    var detaildProduct: MutableLiveData<ResponseDataProductItem> = MutableLiveData()
    var getldProduct: MutableLiveData<List<ResponseDataProductItem>> = MutableLiveData()
    var putldProduct: MutableLiveData<ResponseDataProductItem> = MutableLiveData()
    var postldProduct: MutableLiveData<ResponseDataProductItem> = MutableLiveData()
    var deleteldProduct: MutableLiveData<String> = MutableLiveData()

    init {
        callAllProduct()
    }

    fun callAllProduct() {
        GlobalScope.async {
            RetrofitProduct.instance.getAllProduct()
                .enqueue(object : Callback<List<ResponseDataProductItem>> {
                    override fun onResponse(
                        call: Call<List<ResponseDataProductItem>>,
                        response: Response<List<ResponseDataProductItem>>
                    ) {
                        if (response.isSuccessful) {
                            getldProduct.postValue(response.body())
                        } else {
                            getldProduct.postValue(null)
                        }
                    }

                    override fun onFailure(
                        call: Call<List<ResponseDataProductItem>>,
                        t: Throwable
                    ) {
                        getldProduct.postValue(null)
                    }
                })
        }
    }

    fun callPostProduct(name: String, category: String, stock: Int, price: Int, desc: String, image: String) {
        RetrofitProduct.instance.addProduct(DataProduct(name, category, stock, price, desc, image))
            .enqueue(object : Callback<ResponseDataProductItem> {
                override fun onResponse(
                    call: Call<ResponseDataProductItem>,
                    response: Response<ResponseDataProductItem>
                ) {
                    if (response.isSuccessful) {
                        postldProduct.postValue(response.body())
                    } else {
                        postldProduct.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataProductItem>, t: Throwable) {
                    postldProduct.postValue(null)
                }
            })
    }

    fun callPutProduct(id: String, name: String, category: String, stock: Int, price: Int, desc: String, image: String) {
        RetrofitProduct.instance.updateProduct(id, DataProduct(name, category, stock, price, desc, image))
            .enqueue(object : Callback<ResponseDataProductItem> {
                override fun onResponse(
                    call: Call<ResponseDataProductItem>,
                    response: Response<ResponseDataProductItem>
                ) {
                    if (response.isSuccessful) {
                        putldProduct.postValue(response.body())
                    } else {
                        putldProduct.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataProductItem>, t: Throwable) {
                    putldProduct.postValue(null)
                }
            })
    }
    
    fun callDeleteProduct(id: String) {
        GlobalScope.async { 
            RetrofitProduct.instance.deleteProduct(id)
                .enqueue(object :Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful) {
                            deleteldProduct.postValue(response.body())
                        } else {
                            deleteldProduct.postValue(null)
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        deleteldProduct.postValue(null)
                    }
                })
        }
    }

    fun callDetailApiProduct(id : String){
        RetrofitProduct.instance.getDetail(id)
            .enqueue(object : Callback<ResponseDataProductItem>{
                override fun onResponse(
                    call: Call<ResponseDataProductItem>,
                    response: Response<ResponseDataProductItem>
                ) {
                    if (response.isSuccessful){
                        detaildProduct.postValue(response.body())
                    }else{
                        detaildProduct.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataProductItem>, t: Throwable) {
                    detaildProduct.postValue(null)
                }

            })
    }
}