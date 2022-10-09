package binar.academy.chapter5challenge.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import binar.academy.chapter5challenge.databinding.ActivityLoginBinding
import binar.academy.chapter5challenge.model.ResponseDataUserItem
import binar.academy.chapter5challenge.network.RetrofitUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        // Button Login
        binding.btnLogin.setOnClickListener {
            val edUsername = binding.loginUsername.text.toString()
            val edPassword = binding.loginPassword.text.toString()

            readAccount(edUsername, edPassword)
        }

        // Register Option
        binding.registerHere.setOnClickListener {
            val move = Intent(this, RegisterActivity :: class.java)
            startActivity(move)
        }
    }

    // It will throws a response message for the activity performed by the user
    fun emptyField(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
    }

    // Method for checking data user
    private fun readAccount(username: String, password: String) {
        RetrofitUser.instance.getAllUser()
            .enqueue(object : Callback<List<ResponseDataUserItem>> {
                override fun onResponse(
                    call: Call<List<ResponseDataUserItem>>,
                    response: Response<List<ResponseDataUserItem>>
                ) {
                    var data = false

                    if(response.isSuccessful){
                        if(response.body() != null){
                            val respon = response.body()
                            for (i in 0 until respon!!.size){
                                if(respon[i].email.equals(username, ignoreCase = true) && respon[i].password.equals(password, ignoreCase = true)){
                                    data = true

                                    val addUser = sharedPref.edit()
                                    addUser.putString("email", respon[i].email)
                                    addUser.putString("username", respon[i].username)
                                    addUser.putString("password", password)
                                    addUser.apply()

                                    emptyField("Berhasil Login !")
                                    val move = Intent(this@LoginActivity, MainActivity :: class.java)
                                    startActivity(move)
                                }
                            }
                            if(!data) emptyField("Username atau Password Salah !")
                        }
                        else emptyField("Data Kosong !")
                    }
                    else emptyField("Gagal Memuat Data !")
                }

                override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {}
            })
    }
}