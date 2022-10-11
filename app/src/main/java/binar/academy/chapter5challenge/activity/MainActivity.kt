package binar.academy.chapter5challenge.activity

import android.annotation.SuppressLint
import android.content.Context
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

    private lateinit var viewModel: ViewModelProduct
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ViewModelProduct::class.java]
        binding.rvProduct.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )

        // It will throw the user's username at Main Page
        val sharedPref = getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        binding.mainUsername.text = sharedPref.getString("username", "")

        // Button Add (Create)
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateActivity::class.java)
            startActivity(intent)
        }

        // Button Logout
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

        binding.mainUsername.setOnClickListener {
            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(intent)
        }

        viewModeltoAdapter()
    }

    override fun onResume() {
        super.onResume()
        viewModeltoAdapter()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun viewModeltoAdapter() {
        viewModel.getldProduct.observe(this, Observer {
            Log.d("response", "viewModeltoAdapter : $it")
            if (it != null) {
                productAdapter = ProductAdapter(it)
                binding.rvProduct.adapter = productAdapter

                productAdapter.onDelete = {
                    viewModel.callDeleteProduct(it)
                    viewModel.deleteldProduct.observe(this, Observer {
                        Toast.makeText(this, "Data berhasil dihapus !", Toast.LENGTH_SHORT).show()
                    })
                }

                productAdapter.onEdit = {
                    val dataProduct = it
                    val edit = Intent(this, UpdateActivity :: class.java)
                    intent.putExtra("update", dataProduct)
                    startActivity(edit)
                }

                productAdapter.onDetail = {
                    val dataProduct = it
                    val detail = Intent(this, DetailActivity::class.java)
                    intent.putExtra("detail", dataProduct)
                    startActivity(detail)
                }
                productAdapter.notifyDataSetChanged()
                /*
                Notifies the attached observers that the underlying data has been changed
                and any View reflecting the data set should refresh itself
                */
            }
        })
        viewModel.callAllProduct()
    }
}