package binar.academy.chapter5challenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.academy.chapter5challenge.databinding.ActivityLoginBinding
import binar.academy.chapter5challenge.model.ResponseDataUserItem

class UserAdapter(var listUser : List<ResponseDataUserItem>): RecyclerView.Adapter<UserAdapter.ViewHolder>(){
    class ViewHolder(var binding: ActivityLoginBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ActivityLoginBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val id = listUser[position].id
        val username = listUser[position].username
        val email = listUser[position].email
        val password = listUser[position].password

        var edUsername = holder.binding.loginUsername.text.toString()
        var edPass = holder.binding.loginPassword.text.toString()
    }

    override fun getItemCount(): Int = listUser.size

}