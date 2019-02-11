package com.rodolfogusson.bankapp.login

import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.LoginDataValidator
import com.rodolfogusson.bankapp.login.domain.Validator
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginDataValidatorUnitTest {

    private lateinit var validator: Validator
    private var validCPF = "52998224725"
    private var validPassword = "A&1"

    @Before
    fun setup() {
        validator = LoginDataValidator()
    }

    @Test
    fun `a valid CPF should be valid`() {
        //WHEN
        val valid = validator.validate(LoginData(validCPF, validPassword))

        //THEN
        assertTrue(valid)
    }

    @Test
    fun `a CPF, separated by dots and dash in the correct format, should be valid`() {
        //GIVEN
        val validCPFDotsDash = "529.982.247-25"

        //WHEN
        val valid = validator.validate(
            LoginData(
                validCPFDotsDash,
                validPassword
            )
        )

        //THEN
        assertTrue(valid)
    }

    @Test
    fun `a CPF with only numbers that does not contain 11 digits, should be invalid`() {
        //GIVEN
        val cpf12 = "123456789123"
        val cpf10 = "1234567890"
        val cpf0 = ""

        val user1 = LoginData(cpf12, validPassword)
        val user2 = LoginData(cpf10, validPassword)
        val user3 = LoginData(cpf0, validPassword)

        //WHEN
        val cpf12isValid = validator.validate(user1)
        val cpf10isValid = validator.validate(user2)
        val cpf0isValid = validator.validate(user3)

        //THEN
        assertFalse(cpf12isValid)
        assertFalse(cpf10isValid)
        assertFalse(cpf0isValid)
    }


    @Test
    fun `a CPF that doesn't contain only numbers, dots and dashes should be invalid`() {
        //GIVEN
        val invalidCPF1 = "529.982.A247-25"
        val invalidCPF2 = "529.982.A47-25"

        //WHEN
        val isValidCPF1 = validator.validate(
            LoginData(
                invalidCPF1,
                validPassword
            )
        )
        val isValidCPF2 = validator.validate(
            LoginData(
                invalidCPF2,
                validPassword
            )
        )

        //THEN
        assertFalse(isValidCPF1)
        assertFalse(isValidCPF2)
    }

    @Test
    fun `a CPF containing only repeated numbers should be invalid`(){
        //GIVEN
        val cpf = "11111111111"

        //WHEN
        val valid = validator.validate(LoginData(cpf, validPassword))

        //THEN
        assertFalse(valid)
    }

    @Test
    fun `a valid email should be valid`() {
        //GIVEN
        val validEmail = "email@domain.com"

        //WHEN
        val valid = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertTrue(valid)
    }

    @Test
    fun `an email with domain and subdomain should be valid`() {
        //GIVEN
        val validEmail = "email@subdomain.domain.com"

        //WHEN
        val valid = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertTrue(valid)
    }

    @Test
    fun `an email with dash should be valid`() {
        //GIVEN
        val validEmail = "firstname-lastname@domain.com"

        //WHEN
        val valid = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertTrue(valid)
    }

    @Test
    fun `an email with dot should be valid`() {
        //GIVEN
        val validEmail = "firstname.lastname@domain.com"

        //WHEN
        val valid = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertTrue(valid)
    }

    @Test
    fun `an email with IP inside brackets should be valid`() {
        //GIVEN
        val validEmail = "email@[123.123.123.123]"

        //WHEN
        val valid = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertTrue(valid)
    }

    @Test
    fun `an email missing @ sign and domain should be invalid`() {
        //GIVEN
        val validEmail = "plainaddress"

        //WHEN
        val valid = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertFalse(valid)
    }

    @Test
    fun `an email with garbage before dot com should be invalid`() {
        //GIVEN
        val validEmail = "#@%^%#\\$@#\\$@#.com"

        //WHEN
        val valid = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertFalse(valid)
    }

    @Test
    fun `an email missing username should be invalid`() {
        //GIVEN
        val validEmail = "@domain.com"

        //WHEN
        val valid = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertFalse(valid)
    }

    @Test
    fun `an email missing @ sign should be invalid`() {
        //GIVEN
        val validEmail = "email.domain.com"

        //WHEN
        val valid = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertFalse(valid)
    }

    @Test
    fun `an email with multiple dots should be invalid`() {
        //GIVEN
        val validEmail = "email..email@domain.com"

        //WHEN
        val valid = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertFalse(valid)
    }

    @Test
    fun `a password with at least 1 capital letter, 1 special character and 1 alphanumeric character should be valid`() {
        //WHEN
        val valid = validator.validate(LoginData(validCPF, validPassword))

        //THEN
        assertTrue(valid)
    }

    @Test
    fun `a password without a capital letter should be invalid`() {
        //GIVEN
        val password = "a&a"

        //WHEN
        val valid = validator.validate(LoginData(validCPF, password))

        //THEN
        assertFalse(valid)
    }

    @Test
    fun `a password without a special character should be invalid`() {
        //GIVEN
        val password = "abc123ABC"

        //WHEN
        val valid = validator.validate(LoginData(validCPF, password))

        //THEN
        assertFalse(valid)
    }

    @Test
    fun `a password without an alphanumeric character should be invalid`() {
        //GIVEN
        val password = "&#$&$%"

        //WHEN
        val valid = validator.validate(LoginData(validCPF, password))

        //THEN
        assertFalse(valid)
    }
}