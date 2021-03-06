package com.rodolfogusson.bankapp.login

import com.rodolfogusson.bankapp.login.domain.LoginData
import com.rodolfogusson.bankapp.login.domain.LoginDataValidator
import com.rodolfogusson.bankapp.login.domain.LoginDataValidatorInput
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginDataValidatorUnitTest {

    private lateinit var validator: LoginDataValidatorInput
    private var validCPF = "52998224725"
    private var validPassword = "A&1"

    @Before
    fun setUp() {
        validator = LoginDataValidator()
    }

    @Test
    fun `a valid CPF should be valid`() {
        //WHEN
        val validation = validator.validate(LoginData(validCPF, validPassword))

        //THEN
        assertTrue(validation.isValid)
    }

    @Test
    fun `a CPF, separated by dots and dash in the correct format, should be valid`() {
        //GIVEN
        val validCPFDotsDash = "529.982.247-25"

        //WHEN
        val validation = validator.validate(
            LoginData(
                validCPFDotsDash,
                validPassword
            )
        )

        //THEN
        assertTrue(validation.isValid)
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
        val cpf12Validation = validator.validate(user1)
        val cpf10Validation = validator.validate(user2)
        val cpf0Validation = validator.validate(user3)

        //THEN
        assertFalse(cpf12Validation.isValid)
        assertFalse(cpf10Validation.isValid)
        assertFalse(cpf0Validation.isValid)
    }


    @Test
    fun `a CPF that doesn't contain only numbers, dots and dashes should be invalid`() {
        //GIVEN
        val invalidCPF1 = "529.982.A247-25"
        val invalidCPF2 = "529.982.A47-25"

        //WHEN
        val cpf1Validation = validator.validate(
            LoginData(
                invalidCPF1,
                validPassword
            )
        )
        val cpf2Validation = validator.validate(
            LoginData(
                invalidCPF2,
                validPassword
            )
        )

        //THEN
        assertFalse(cpf1Validation.isValid)
        assertFalse(cpf2Validation.isValid)
    }

    @Test
    fun `a CPF containing only repeated numbers should be invalid`(){
        //GIVEN
        val cpf = "11111111111"

        //WHEN
        val validation = validator.validate(LoginData(cpf, validPassword))

        //THEN
        assertFalse(validation.isValid)
    }

    @Test
    fun `a valid email should be valid`() {
        //GIVEN
        val validEmail = "email@domain.com"

        //WHEN
        val validation = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertTrue(validation.isValid)
    }

    @Test
    fun `an email with domain and subdomain should be valid`() {
        //GIVEN
        val validEmail = "email@subdomain.domain.com"

        //WHEN
        val validation = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertTrue(validation.isValid)
    }

    @Test
    fun `an email with dash should be valid`() {
        //GIVEN
        val validEmail = "firstname-lastname@domain.com"

        //WHEN
        val validation = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertTrue(validation.isValid)
    }

    @Test
    fun `an email with dot should be valid`() {
        //GIVEN
        val validEmail = "firstname.lastname@domain.com"

        //WHEN
        val validation = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertTrue(validation.isValid)
    }

    @Test
    fun `an email with IP inside brackets should be valid`() {
        //GIVEN
        val validEmail = "email@[123.123.123.123]"

        //WHEN
        val validation = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertTrue(validation.isValid)
    }

    @Test
    fun `an email missing @ sign and domain should be invalid`() {
        //GIVEN
        val validEmail = "plainaddress"

        //WHEN
        val validation = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertFalse(validation.isValid)
    }

    @Test
    fun `an email with garbage before dot com should be invalid`() {
        //GIVEN
        val validEmail = "#@%^%#\\$@#\\$@#.com"

        //WHEN
        val validation = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertFalse(validation.isValid)
    }

    @Test
    fun `an email missing username should be invalid`() {
        //GIVEN
        val validEmail = "@domain.com"

        //WHEN
        val validation = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertFalse(validation.isValid)
    }

    @Test
    fun `an email missing @ sign should be invalid`() {
        //GIVEN
        val validEmail = "email.domain.com"

        //WHEN
        val validation = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertFalse(validation.isValid)
    }

    @Test
    fun `an email with multiple dots should be invalid`() {
        //GIVEN
        val validEmail = "email..email@domain.com"

        //WHEN
        val validation = validator.validate(LoginData(validEmail, validPassword))

        //THEN
        assertFalse(validation.isValid)
    }

    @Test
    fun `a password with at least 1 capital letter, 1 special character and 1 alphanumeric character should be valid`() {
        //WHEN
        val validation = validator.validate(LoginData(validCPF, validPassword))

        //THEN
        assertTrue(validation.isValid)
    }

    @Test
    fun `a password without a capital letter should be invalid`() {
        //GIVEN
        val password = "a&a"

        //WHEN
        val validation = validator.validate(LoginData(validCPF, password))

        //THEN
        assertFalse(validation.isValid)
    }

    @Test
    fun `a password without a special character should be invalid`() {
        //GIVEN
        val password = "abc123ABC"

        //WHEN
        val validation = validator.validate(LoginData(validCPF, password))

        //THEN
        assertFalse(validation.isValid)
    }

    @Test
    fun `a password without an alphanumeric character should be invalid`() {
        //GIVEN
        val password = "&#$&$%"

        //WHEN
        val validation = validator.validate(LoginData(validCPF, password))

        //THEN
        assertFalse(validation.isValid)
    }
}