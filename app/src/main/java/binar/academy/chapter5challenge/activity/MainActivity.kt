package binar.academy.chapter5challenge.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import binar.academy.chapter5challenge.adapter.ProductAdapter
import binar.academy.chapter5challenge.databinding.ActivityMainBinding
import binar.academy.chapter5challenge.viewmodel.ViewModelProduct

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUsername.text =

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, CreateActivity :: class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModeltoAdapter()
    }

    fun viewModeltoAdapter() {
        val viewModel = ViewModelProvider(this).get(ViewModelProduct :: class.java)

        viewModel.getldProduct.observe(this, Observer {
            Log.d("response","viewModeltoAdapter : " + it.toString())

            if (it != null) {
                binding.rvProduct.layoutManager = LinearLayoutManager(
                    this, LinearLayoutManager.VERTICAL, false
                )

                productAdapter = ProductAdapter(it)
                binding.rvProduct.adapter = productAdapter

                productAdapter.onDelete={
                    viewModel.callDeleteProduct(it)
                    viewModel.deleteldProduct.observe(this, Observer {
                        Toast.makeText(this, "Data has been deleted", Toast.LENGTH_SHORT).show()
                    })
                }

                productAdapter.onDetail={
                    var dataProduct = it
                    var move = Intent(this, DetailActivity :: class.java)
                    startActivity(move)
                }
            }
        })
    }
}