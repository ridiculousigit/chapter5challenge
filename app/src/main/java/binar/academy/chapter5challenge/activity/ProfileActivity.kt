package binar.academy.chapter5challenge.activity

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import binar.academy.chapter5challenge.databinding.ActivityProfileBinding
import binar.academy.chapter5challenge.viewmodel.ViewModelUser
import java.text.SimpleDateFormat
import java.util.*

class ProfileActivity : AppCompatActivity() {

    private var birthday = ""
    lateinit var binding: ActivityProfileBinding
    lateinit var sharedPref: SharedPreferences
    private var idUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[ViewModelUser::class.java]
        sharedPref = getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.apply {
            registerUsername.setText(sharedPref.getString("username", ""))
            profileName.setText(sharedPref.getString("fullname", ""))
            profileBirthday.setText(sharedPref.getString("birthday", ""))
            profileAddress.setText(sharedPref.getString("address", ""))
            idUser = sharedPref.getString("id", "").toString()
            profileBirthday.setOnClickListener {
                DatePickerDialog(
                    this@ProfileActivity,
                    { datePicker, yearSelected, monthSelected, daySelected ->
                        // For show in Text View
                        val calendarFormat = Calendar.getInstance()
                        calendarFormat.set(yearSelected, monthSelected, daySelected);
                        val format = SimpleDateFormat("dd MMM yyyy")
                        val strDate = format.format(calendarFormat.time)
                        profileBirthday.setText(strDate)
                        birthday = strDate
                    }, year, month, day
                ).show()
            }

            btnUpdate.setOnClickListener {
                viewModel.update(
                    idUser,
                    profileName.text.toString(),
                    profileAddress.text.toString(),
                    profileBirthday.text.toString()
                )
                viewModel.updateUser.observe(this@ProfileActivity) { response ->
                    if (response != null) {
                        Toast.makeText(
                            this@ProfileActivity,
                            "Update Berhasil",
                            Toast.LENGTH_SHORT
                        ).show()
                        val addUser = sharedPref.edit()
                        addUser.putString("email", response.email)
                        addUser.putString("username", response.username)
                        addUser.putString("password", response.password)
                        addUser.putString("fullname", response.fullname)
                        addUser.putString("birthday", response.birthday)
                        addUser.putString("address", response.address)
                        addUser.putString("id", response.id)
                        addUser.apply()
                        val intent = Intent(this@ProfileActivity, ProfileActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@ProfileActivity,
                            "Update Gagal",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

    }

}