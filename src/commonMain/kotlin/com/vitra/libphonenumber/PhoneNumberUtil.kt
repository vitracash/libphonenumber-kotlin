package com.vitra.libphonenumber

expect class PhoneNumberUtil private constructor() {
    fun parse(phoneNumber: String, defaultRegion: String): PhoneNumber
    fun isValidNumber(phoneNumber: PhoneNumber): Boolean
    fun format(phoneNumber: PhoneNumber, format: PhoneNumberFormat): String

    fun getAsYouTypeFormatter(regionCode: String): AsYouTypeFormatter

    companion object {
        fun getInstance(): PhoneNumberUtil
    }
}
