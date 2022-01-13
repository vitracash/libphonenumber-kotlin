package com.vitra.libphonenumber

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PhoneNumberUtilTests {
    private val phoneNumberUtil = PhoneNumberUtil.getInstance()

    @Test
    fun shouldParseAValidPhoneNumber() {
        val countryCode = 44
        val nationalNumber = 123412341234

        val phoneNumber = phoneNumberUtil.parse("+$countryCode$nationalNumber", "UK")

        assertEquals(countryCode, phoneNumber.countryCode)
        assertEquals(nationalNumber, phoneNumber.nationalNumber)
    }

    @Test
    fun shouldAllowISO3RegionCode() {
        val countryCode = 44
        val nationalNumber = 123412341234

        val phoneNumber = phoneNumberUtil.parse("+$countryCode$nationalNumber", "GBR")

        assertEquals(countryCode, phoneNumber.countryCode)
        assertEquals(nationalNumber, phoneNumber.nationalNumber)
    }

    @Test
    fun shouldThrowIfNumberIsInvalid() {
        assertFailsWith<NumberParseException> { phoneNumberUtil.parse("1234", "UK") }
    }

    @Test
    fun shouldAllowPhoneNumbersWithADifferentRegionThanProvided() {
        val germanCountryCode = 49
        val nationalNumber = 123412341234

        val phoneNumber = phoneNumberUtil.parse("+$germanCountryCode$nationalNumber", "GBR")

        assertEquals(germanCountryCode, phoneNumber.countryCode)
        assertEquals(nationalNumber, phoneNumber.nationalNumber)
    }

    @Test
    fun shouldFormatPhoneNumbersCorrectly() {
        val phoneNumber = PhoneNumber(countryCode = 44, nationalNumber = 123412341234)

        val formatted = phoneNumberUtil.format(phoneNumber, PhoneNumberFormat.INTERNATIONAL)

        assertEquals("+${phoneNumber.countryCode} ${phoneNumber.nationalNumber}", formatted)
    }
}
