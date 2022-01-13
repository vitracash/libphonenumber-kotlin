package com.vitra.libphonenumber

import kotlin.test.Test
import kotlin.test.assertEquals

class AsYouTypeFormatterTests {
    private val formatter = PhoneNumberUtil.getInstance().getAsYouTypeFormatter("AT")

    @Test
    fun shouldFormatTheGivenPhoneNumber() {
        val countryCode = "+44"
        countryCode.let {
            var formatted = ""
            for (digit in it) {
                formatted = formatter.inputDigit(digit)
            }

            assertEquals("$countryCode ", formatted)
        }

        val carrierPrefix = "1234"
        carrierPrefix.let {
            var formatted = ""
            for (digit in it) {
                formatted = formatter.inputDigit(digit)
            }

            assertEquals("$countryCode $carrierPrefix", formatted)
        }

        val nationalNumber = "12341"
        nationalNumber.let {
            var formatted = ""
            for (digit in it) {
                formatted = formatter.inputDigit(digit)
            }

            assertEquals("$countryCode $carrierPrefix $nationalNumber", formatted)
        }
    }
}
