package binar.academy.chapter5challenge.viewmodel

import binar.academy.chapter5challenge.model.ResponseDataProductItem
import binar.academy.chapter5challenge.network.RestfulProduct
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class ViewModelProductTest {

    lateinit var service: RestfulProduct
    lateinit var viewModel: ViewModelProduct

    @Before
    fun setUp(){
        service = mockk()
        viewModel = ViewModelProduct()
    }

    @Test
    fun getAllProducts(){
        val response = mockk<Call<List<ResponseDataProductItem>>>()

        every {
            service.getAllProduct()
        } returns response

        val result = service.getAllProduct()
        verify {
            service.getAllProduct()
        }
        Assert.assertEquals(result, response)
    }
}