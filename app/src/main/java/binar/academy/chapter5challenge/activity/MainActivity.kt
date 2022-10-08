package binar.academy.chapter5challenge.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import binar.academy.chapter5challenge.R
import binar.academy.chapter5challenge.adapter.ProductAdapter
import binar.academy.chapter5challenge.databinding.ActivityMainBinding
import binar.academy.chapter5challenge.viewmodel.ViewModelProduct

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, CreateActivity :: class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    fun viewModeltoAdapter() {
        val viewModel = ViewModelProvider(this).get(ViewModelProduct :: class.java)

        viewModel.getldProduct().observe(this, Observer {
            Log.d("response","viewModeltoAdapter : " + it.toString())

        }
        if (it != null) {
            binding.rvProduct.layoutManager = LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false
            )

            adapter = ProductAdapter(it)
    }
}