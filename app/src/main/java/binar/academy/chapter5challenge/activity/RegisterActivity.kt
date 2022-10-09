package binar.academy.chapter5challenge.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import binar.academy.chapter5challenge.databinding.ActivityRegisterBinding
import binar.academy.chapter5challenge.viewmodel.ViewModelUser

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Button Register
        binding.btnRegister.setOnClickListener {
            val edUsername = binding.registerUsername.text.toString()
            val edEmail = binding.registerEmail.text.toString()
            val edPassword = binding.registerPassword.text.toString()
            val edConfirm = binding.registerConfirm.text.toString()

            // It will identifying whether pass == confirm pass
            if(edPassword.equals(edConfirm)) {
                addUser(edUsername, edEmail, edPassword)
                val move = Intent(this, LoginActivity :: class.java)
                startActivity(move)
            } else errorPass("Password tidak cocok !")
        }

        // Login Option
        binding.loginHere.setOnClickListener {
            val move = Intent(this, LoginActivity :: class.java)
            startActivity(move)
        }
    }

    // It will throws a response message for the activity performed by the user
    private fun errorPass(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Method for create a new user data
    private fun addUser(username: String, email: String, password: String) {
        val viewModel = ViewModelProvider(this)[ViewModelUser :: class.java]
        viewModel.callPostUser(username, email, password)
        viewModel.addldUser().observe(this, {
            if (it != null) {
                Toast.makeText(this, "Akun berhasil dibuat !", Toast.LENGTH_SHORT).show()
            }
        })
    }
}