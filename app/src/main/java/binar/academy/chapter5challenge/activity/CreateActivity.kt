package binar.academy.chapter5challenge.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import binar.academy.chapter5challenge.databinding.ActivityCreateBinding
import binar.academy.chapter5challenge.viewmodel.ViewModelProduct

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Button Create
        binding.btnCreate.setOnClickListener {
            createProduct()
        }
    }

    // Method for create a new data product
    private fun createProduct() {
        val name = binding.addName.text.toString()
        val category = binding.addCategory.text.toString()
        val stock = binding.addStock.text.toString().toInt()
        val price = binding.addPrice.text.toString().toInt()
        val desc = binding.addDescription.text.toString()
        val image = binding.addImage.text.toString()

        val viewModel = ViewModelProvider(this)[ViewModelProduct :: class.java]
        viewModel.callPostProduct(name, category, stock, price, desc, image)
        viewModel.postldProduct.observe(this, Observer {
            if (it != null) {
                Toast.makeText(this, "Produk baru berhasil ditambahkan !", Toast.LENGTH_SHORT).show()
                Log.d("addProduct", it.toString())
                val create = Intent(this, MainActivity :: class.java)
                startActivity(create)
            }
        })
    }
}