package binar.academy.chapter5challenge.model


import com.google.gson.annotations.SerializedName

data class ResponseDataUserItem(
    /*@SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String*/
    @SerializedName("address")
    val address: String,
    @SerializedName("birthday")
    val birthday: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)