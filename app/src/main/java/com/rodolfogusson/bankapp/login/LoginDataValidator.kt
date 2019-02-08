package com.rodolfogusson.bankapp.login

interface Validator {
    fun validate(user: User): Boolean
}

class LoginDataValidator : Validator{

    override fun validate(user: User): Boolean {
        return validateLogin(user.login) //&& validatePassword(user.password)
    }

//    private fun validatePassword(password: String): Boolean {
//        return true
//    }

    private fun validateLogin(login: String): Boolean {
        //If login doesn't contain only numbers, dots and dash, it's invalid
        if (!login.matches("[-0-9.]+".toRegex())) return false

        //If there aren't 11 numbers in the string, it's invalid
        val numericLogin = login.replace("[^0-9]".toRegex(),"")
        if (numericLogin.length != 11) return false

        //If there are only repeated numbers, it's invalid
        if (numericLogin.matches("(.)\\1+".toRegex())) return false

        //Validate the first verification digit
        var sum1 = 0
        for (i in 0..8) {
            sum1 += (10-i) * numericLogin[i].toString().toInt()
        }
        var mod1 = (sum1 * 10) % 11
        if (mod1 == 10) mod1 = 0
        if (mod1 != numericLogin[9].toString().toInt()) return false

        //Validate the second verification digit
        var sum2 = 0
        for (i in 0..9) {
            sum2 += (11-i) * numericLogin[i].toString().toInt()
        }
        var mod2 = (sum2 * 10) % 11
        if (mod2 == 10) mod2 = 0
        if (mod2 != numericLogin[10].toString().toInt()) return false

        return true
    }
}