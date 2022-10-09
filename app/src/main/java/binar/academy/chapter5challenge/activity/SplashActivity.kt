package binar.academy.chapter5challenge.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import binar.academy.chapter5challenge.R
import binar.academy.chapter5challenge.databinding.ActivitySplashBinding
import com.bumptech.glide.Glide

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(R.drawable.anim_splash).into(binding.ivSplash)

        sharedPref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        val dbUser = sharedPref.getString("username", "")

        if(dbUser == "") {
            val dbUsername = sharedPref.getString("username", "You!")
            val bundle = Bundle()
            bundle.putString("username", dbUsername)

            Handler().postDelayed({
                val intent = Intent(this, LoginActivity :: class.java)
                startActivity(intent)
                finish()
            }, 1000)
        }
        else {
            Handler().postDelayed({
                val intent = Intent(this, MainActivity :: class.java)
                startActivity(intent)
                finish()
            }, 2000)
        }
    }
}