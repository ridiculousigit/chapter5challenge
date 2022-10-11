package binar.academy.chapter5challenge.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import binar.academy.chapter5challenge.databinding.ActivityDetailBinding
import binar.academy.chapter5challenge.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}