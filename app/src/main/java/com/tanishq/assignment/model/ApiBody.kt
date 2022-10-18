package com.tanishq.assignment.model


import com.google.gson.annotations.SerializedName

data class ApiBody(
    @SerializedName("candidateEmail")
    val candidateEmail: String,
    @SerializedName("dob")
    val dob: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String
)