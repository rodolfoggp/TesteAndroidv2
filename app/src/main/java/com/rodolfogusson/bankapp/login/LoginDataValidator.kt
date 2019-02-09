package com.rodolfogusson.bankapp.login

interface Validator {
    fun validate(loginData: LoginData): Boolean
}

class LoginDataValidator : Validator{

    override fun validate(loginData: LoginData): Boolean {
        return validateLogin(loginData.login) && validatePassword(loginData.password)
    }

    private fun validateLogin(login: String): Boolean {
        return validateCPF(login) || validateEmail(login)
    }

    private fun validateCPF(login: String): Boolean {
        //If login doesn't contain only numbers, dots and dash, it's invalid
        if (!login.matches("[-0-9.]+".toRegex())) return false

        //If there aren't 11 numbers in the string, it's invalid
        val numericLogin = login.replace("[^0-9]".toRegex(), "")
        if (numericLogin.length != 11) return false

        //If there are only repeated numbers, it's invalid
        if (numericLogin.matches("(.)\\1+".toRegex())) return false

        //Validate the first verification digit
        var sum1 = 0
        for (i in 0..8) {
            sum1 += (10 - i) * numericLogin[i].toString().toInt()
        }
        var mod1 = (sum1 * 10) % 11
        if (mod1 == 10) mod1 = 0
        if (mod1 != numericLogin[9].toString().toInt()) return false

        //Validate the second verification digit
        var sum2 = 0
        for (i in 0..9) {
            sum2 += (11 - i) * numericLogin[i].toString().toInt()
        }
        var mod2 = (sum2 * 10) % 11
        if (mod2 == 10) mod2 = 0
        if (mod2 != numericLogin[10].toString().toInt()) return false

        return true
    }

    private fun validateEmail(login: String): Boolean {
        //Email regex for Java, picked from emailregex.com
        val emailRegex = ("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+" +
                "(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*" +
                "|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]" +
                "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" +
                "|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?" +
                "|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]" +
                "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
                .trimMargin()
                .toRegex()
        return login.matches(emailRegex)
    }

    private fun validatePassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Z])(?=.*[a-zA-Z0-9])(?=.*[^a-zA-Z0-9]).+\$".toRegex()
        return passwordRegex.matches(password)
    }
}
