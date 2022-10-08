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

    var ldProduct: MutableLiveData<List<ResponseDataProductItem>> = MutableLiveData()
    private var putldProduct: MutableLiveData<List<ResponseDataProductItem>> = MutableLiveData()
    private var postldProduct: MutableLiveData<ResponseDataProductItem> = MutableLiveData()
    private var deleteldProduct: MutableLiveData<Int> = MutableLiveData()
    var loading = MutableLiveData<Boolean>()

    init {
        callAllProduct()
    }

    fun getldProduct(): MutableLiveData<List<ResponseDataProductItem>> {
        return ldProduct
    }

    fun addldProduct(): MutableLiveData<ResponseDataProductItem> {
        return postldProduct
    }

    fun updateldProduct(): MutableLiveData<List<ResponseDataProductItem>> {
        return putldProduct
    }

    fun deleteldProduct(): MutableLiveData<Int> {
        return deleteldProduct
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
                            ldProduct.postValue(response.body())
                        } else {
                            ldProduct.postValue(null)
                        }
                    }

                    override fun onFailure(
                        call: Call<List<ResponseDataProductItem>>,
                        t: Throwable
                    ) {
                        ldProduct.postValue(null)
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
                        ldProduct.postValue(response.body())
                    } else {
                        ldProduct.postValue(null)
                    }
                    callAllProduct()
                }

                override fun onFailure(call: Call<ResponseDataProductItem>, t: Throwable) {
                    ldProduct.postValue(null)
                    callAllProduct()
                }
            })
    }

    fun callPutProduct(id: Int, name: String, category: String, stock: Int, price: Int, desc: String, image: String) {
        RetrofitProduct.instance.updateProduct(id, DataProduct(name, category, stock, price, desc, image))
            .enqueue(object : Callback<List<ResponseDataProductItem>> {
                override fun onResponse(
                    call: Call<List<ResponseDataProductItem>>,
                    response: Response<List<ResponseDataProductItem>>
                ) {
                    if (response.isSuccessful) {
                        ldProduct.postValue(response.body())
                    } else {
                        ldProduct.postValue(null)
                    }
                    callAllProduct()
                }

                override fun onFailure(call: Call<List<ResponseDataProductItem>>, t: Throwable) {
                    ldProduct.postValue(null)
                }
            })
    }
    
    fun callDeleteProduct(id: Int) {
        GlobalScope.async { 
            RetrofitProduct.instance.deleteProduct(id)
                .enqueue(object :Callback<Int> {
                    override fun onResponse(call: Call<Int>, response: Response<Int>) {
                        if (response.isSuccessful) {
                            ldProduct.postValue(response.body())
                        } else {
                            ldProduct.postValue(null)
                        }
                        callAllProduct()
                    }

                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        ldProduct.postValue(null)
                    }
                })
        }
    }

    fun callDetailApiProduct(id : Int){
        RetrofitProduct.instance.getDetail(id)
            .enqueue(object : Callback<List<ResponseDataProductItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataProductItem>>,
                    response: Response<List<ResponseDataProductItem>>
                ) {
                    if (response.isSuccessful){
                        ldProduct.postValue(response.body())
                    }else{
                        ldProduct.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataProductItem>>, t: Throwable) {
                    ldProduct.postValue(null)
                }

            })
    }
}