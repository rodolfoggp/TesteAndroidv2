package com.rodolfogusson.bankapp.login.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodolfogusson.bankapp.core.network.ApiResponse

data class LoginData(val user: String, val password: String)

@Entity(tableName = "users")
data class User(
    @PrimaryKey val userId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "bankAccount") val bankAccount: String,
    @ColumnInfo(name = "agency") val agency: String,
    @ColumnInfo(name = "balance") val balance: Double,
    @ColumnInfo(name = "loginData") var loginData: LoginData? = null)

data class LoginResponse(val userAccount: User? = null) : ApiResponse()

class LoginRequest{} //todo: USE this class

//class User == LoginResponse
