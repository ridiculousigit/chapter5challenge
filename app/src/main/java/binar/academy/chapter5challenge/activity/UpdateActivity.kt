package binar.academy.chapter5challenge.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import binar.academy.chapter5challenge.databinding.ActivityUpdateBinding
import binar.academy.chapter5challenge.model.ResponseDataProductItem
import binar.academy.chapter5challenge.viewmodel.ViewModelProduct

class UpdateActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateBinding
    lateinit var detail: ResponseDataProductItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detail = intent.getSerializableExtra("update") as ResponseDataProductItem
        binding.updateName.setText(detail.name)
        binding.updateCategory.setText(detail.category)
        binding.updateStock.setText(detail.stock).toString()
        binding.updatePrice.setText(detail.price).toString()
        binding.updateDescription.setText(detail.description)
        binding.updateImage.setText(detail.image)

        binding.btnUpdate.setOnClickListener {
            val productId = detail.id
            val productName = detail.name
            val productCategory = detail.category
            val productStock = detail.stock
            val productPrice = detail.price
            val productDesc = detail.description
            val productImage = detail.image

            updateDataProduct(productId, productName, productCategory, productStock, productPrice, productDesc, productImage)
            finish()
        }
    }

    private fun updateDataProduct(id: String, name: String, category: String, stock: Int, price: Int, desc: String, image: String) {
        val viewModel = ViewModelProvider(this)[ViewModelProduct :: class.java]
        viewModel.callPutProduct(id, name, category, stock, price, desc, image)
        viewModel.putldProduct.observe(this, Observer {
            if (it != null) {
                Toast.makeText(this, "Update nih", Toast.LENGTH_SHORT).show()
            }
        })
    }
}