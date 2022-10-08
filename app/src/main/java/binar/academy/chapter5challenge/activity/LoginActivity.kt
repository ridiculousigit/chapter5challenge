package binar.academy.chapter5challenge.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import binar.academy.chapter5challenge.adapter.UserAdapter
import binar.academy.chapter5challenge.databinding.ActivityLoginBinding
import binar.academy.chapter5challenge.model.ResponseDataUserItem
import binar.academy.chapter5challenge.network.RetrofitUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var sharedPref: SharedPreferences
    lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val edUsername = binding.loginUsername.text.toString()
            val edPassword = binding.loginPassword.text.toString()

            if (edUsername != null && edPassword != null) readAccount(edUsername, edPassword)
            else if (edUsername == null && edPassword == null) emptyField("Please fill all fields !")
        }

        binding.registerHere.setOnClickListener {
            val move = Intent(this, RegisterActivity :: class.java)
            startActivity(move)
        }
    }

    fun emptyField(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
    }

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
                                if(respon[i].username == username && respon[i].password == password){
                                    data = true

                                    //add ke sharedpref
                                    val addUser = sharedPref.edit()
                                    addUser.putString("username", username)
                                    addUser.putString("password", password)
                                    addUser.apply()

                                    emptyField("Login Success!")
                                    val move = Intent(this@LoginActivity, MainActivity :: class.java)
                                    startActivity(move)
                                }
                            }
                            if(!data) emptyField("Wrong Username or Password!")
                        }
                        else emptyField("Empty Response!")
                    }
                    else emptyField("Failed Load Data!")
                }

                override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}