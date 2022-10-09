package binar.academy.chapter5challenge.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import binar.academy.chapter5challenge.databinding.ActivityRegisterBinding
import binar.academy.chapter5challenge.viewmodel.ViewModelUser

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("userRegister", Context.MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {

            var edUsername = binding.registerUsername.text.toString()
            var edEmail = binding.registerEmail.text.toString()
            var edPassword = binding.registerPassword.text.toString()
            var edConfirm = binding.registerConfirm.text.toString()

            if(edPassword.equals(edConfirm)) {
                addUser(edUsername, edEmail, edPassword)
                var move = Intent(this, LoginActivity :: class.java)
                startActivity(move)
            } else errorPass("Password doesn't match !")
        }

        binding.loginHere.setOnClickListener {
            val move = Intent(this, LoginActivity :: class.java)
            startActivity(move)
        }
    }

    fun errorPass(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun addUser(username: String, email: String, password: String) {
        var viewModel = ViewModelProvider(this).get(ViewModelUser :: class.java)
        viewModel.callPostUser(username, email, password)
        viewModel.addldUser().observe(this, {
            if (it != null) {
                val addUser = sharedPref.edit()
                addUser.putString("usernameRegister", username)
                addUser.putString("emailRegister", email)
                addUser.putString("passwordRegister", password)
                addUser.apply()
                Toast.makeText(this, "Account has been created !", Toast.LENGTH_SHORT).show()
            }
        })
    }
}