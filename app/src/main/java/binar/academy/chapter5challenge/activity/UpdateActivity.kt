package binar.academy.chapter5challenge.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import binar.academy.chapter5challenge.R
import binar.academy.chapter5challenge.databinding.ActivityMainBinding
import binar.academy.chapter5challenge.databinding.ActivityUpdateBinding
import binar.academy.chapter5challenge.model.ResponseDataProductItem

class UpdateActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateBinding
    lateinit var detail: ResponseDataProductItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
    }
}