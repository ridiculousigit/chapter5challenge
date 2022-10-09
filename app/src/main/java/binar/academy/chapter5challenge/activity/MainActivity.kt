package binar.academy.chapter5challenge.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

    private lateinit var viewModel: ViewModelProduct
    lateinit var binding: ActivityMainBinding
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ViewModelProduct::class.java)
        binding.rvProduct.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )

        val sharedPref = getSharedPreferences("userRegister", Context.MODE_PRIVATE)
        binding.mainUsername.text = sharedPref.getString("emailRegister", "")

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            with(sharedPref.edit()) {
                this.putString("username", "")
                this.putString("password", "")
                this.apply()
            }
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        viewModeltoAdapter()
    }

    fun viewModeltoAdapter() {
        viewModel.getldProduct.observe(this, Observer {
            Log.d("response", "viewModeltoAdapter : " + it.toString())
            if (it != null) {
                productAdapter = ProductAdapter(it)
                binding.rvProduct.adapter = productAdapter

                productAdapter.onDelete = {
                    viewModel.callDeleteProduct(it)
                    viewModel.deleteldProduct.observe(this, Observer {
                        Toast.makeText(this, "Data has been deleted", Toast.LENGTH_SHORT).show()
                    })
                }

                productAdapter.onDetail = {
                    var dataProduct = it
                    var move = Intent(this, DetailActivity::class.java)
                    startActivity(move)
                }
            }
        })
    }
}