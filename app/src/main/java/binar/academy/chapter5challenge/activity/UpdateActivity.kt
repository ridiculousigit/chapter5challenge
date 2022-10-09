package binar.academy.chapter5challenge.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import binar.academy.chapter5challenge.databinding.ActivityUpdateBinding
import binar.academy.chapter5challenge.model.ResponseDataProductItem
import binar.academy.chapter5challenge.viewmodel.ViewModelProduct

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var dataProduct: ResponseDataProductItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get product data by ID from API
        dataProduct = intent.getSerializableExtra("update") as ResponseDataProductItem
        binding.updateName.setText(dataProduct.name)
        binding.updateCategory.setText(dataProduct.category)
        binding.updateStock.setText(dataProduct.stock.toString())
        binding.updatePrice.setText(dataProduct.price.toString())
        binding.updateDescription.setText(dataProduct.description)
        binding.updateImage.setText(dataProduct.image)

        // Button Update
        binding.btnUpdate.setOnClickListener {
            val productId = dataProduct.id
            val productName = binding.updateName.text.toString()
            val productCategory = binding.updateCategory.text.toString()
            val productStock = binding.updateStock.text.toString().toInt()
            val productPrice = binding.updatePrice.text.toString().toInt()
            val productDesc = binding.updateDescription.text.toString()
            val productImage = binding.updateImage.text.toString()

            updateDataProduct(productId, productName, productCategory, productStock, productPrice, productDesc, productImage)
            finish()
        }
    }

    // Method for updating product data
    private fun updateDataProduct(id: String, name: String, category: String, stock: Int, price: Int, desc: String, image: String) {
        val viewModel = ViewModelProvider(this)[ViewModelProduct :: class.java]
        viewModel.callPutProduct(id, name, category, stock, price, desc, image)
        viewModel.putldProduct.observe(this, Observer {
            if (it != null) {
                Toast.makeText(this, "Data berhasil diperbarui !", Toast.LENGTH_SHORT).show()
                Log.d("update", it.toString())
            }
        })
    }
}