package binar.academy.chapter5challenge.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import binar.academy.chapter5challenge.databinding.ActivityDetailBinding
import binar.academy.chapter5challenge.model.ResponseDataProductItem
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataProduct: ResponseDataProductItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataProduct = intent.getSerializableExtra("detail") as ResponseDataProductItem
        binding.detailHeader.text = dataProduct.name
        binding.detailCategory.text = dataProduct.category
        binding.detailStock.text = dataProduct.stock.toString()
        binding.detailPrice.text = dataProduct.price.toString()
        binding.detailDesc.text = dataProduct.description
        Glide.with(this).load(dataProduct.image).into(binding.detailPict)
    }
}