package com.rodolfogusson.bankapp.login

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
    fun `when a valid CPF is put to validation, it is valid`() {
        //WHEN
        val valid = validator.validate(User(validCPF, validPassword))

        //THEN
        assertTrue(valid)
    }

    @Test
    fun `when a valid CPF, separated by dots and dash in the correct format, is validated, it is valid`() {
        //GIVEN
        val validCPFDotsDash = "529.982.247-25"

        //WHEN
        val valid = validator.validate(User(validCPFDotsDash, validPassword))

        //THEN
        assertTrue(valid)
    }

    @Test
    fun `when CPF with only numbers does not contain 11 digits, it is invalid`() {
        //GIVEN
        val cpf12 = "123456789123"
        val cpf10 = "1234567890"
        val cpf0 = ""

        val user1 = User(cpf12, validPassword)
        val user2 = User(cpf10, validPassword)
        val user3 = User(cpf0, validPassword)

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
    fun `when CPF doesn't contain only numbers, dots and dashes, it is invalid`() {
        //GIVEN
        val invalidCPF1 = "529.982.A247-25"
        val invalidCPF2 = "529.982.A47-25"

        //WHEN
        val isValidCPF1 = validator.validate(User(invalidCPF1, validPassword))
        val isValidCPF2 = validator.validate(User(invalidCPF2, validPassword))

        //THEN
        assertFalse(isValidCPF1)
        assertFalse(isValidCPF2)
    }

    @Test
    fun `when CPF contains only repeated numbers, it is invalid`(){
        //GIVEN
        val cpf = "11111111111"

        //WHEN
        val valid = validator.validate(User(cpf, validPassword))

        //THEN
        assertFalse(valid)
    }
}