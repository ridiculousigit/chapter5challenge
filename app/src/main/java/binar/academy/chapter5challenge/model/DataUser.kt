package binar.academy.chapter5challenge.model

import java.io.Serializable

data class DataUser (
    var username: String,
    var email: String,
    var password: String
): Serializable