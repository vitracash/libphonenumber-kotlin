package com.vitra.libphonenumber

import cocoapods.libPhoneNumber_iOS.NBAsYouTypeFormatter
import cocoapods.libPhoneNumber_iOS.NBPhoneNumber
import cocoapods.libPhoneNumber_iOS.NBPhoneNumberUtil
import platform.Foundation.NSNumber

actual class PhoneNumberUtil {
    private val phoneUtil = NBPhoneNumberUtil.sharedInstance()!!

    actual fun parse(phoneNumber: String, defaultRegion: String): PhoneNumber {
        return phoneUtil.parse(phoneNumber, defaultRegion, null)?.asKotlin()
            ?: throw NumberParseException(phoneNumber)
    }

    actual fun isValidNumber(phoneNumber: PhoneNumber): Boolean = phoneUtil.isValidNumber(phoneNumber.asNative())

    actual fun format(phoneNumber: PhoneNumber, format: PhoneNumberFormat): String {
        return phoneUtil.format(phoneNumber.asNative(), format.ordinal.toLong(), null)!!
    }

    actual fun getAsYouTypeFormatter(regionCode: String) = AsYouTypeFormatter(NBAsYouTypeFormatter(regionCode))

    actual companion object {
        actual fun getInstance() = PhoneNumberUtil()
    }
}

private fun NBPhoneNumber.asKotlin() = PhoneNumber(
    countryCode = countryCode?.intValue,
    extension = extension,
    hasItalianLeadingZero = italianLeadingZero,
    nationalNumber = nationalNumber?.longValue,
    numberOfLeadingZeros = numberOfLeadingZeros?.intValue,
    preferredDomesticCarrierCode = preferredDomesticCarrierCode,
    rawInput = rawInput
)

private fun PhoneNumber.asNative() = NBPhoneNumber().also { phoneNumber ->
    phoneNumber.countryCode = countryCode?.let { NSNumber(it) }
    phoneNumber.extension = extension
    phoneNumber.italianLeadingZero = hasItalianLeadingZero
    phoneNumber.nationalNumber = nationalNumber?.let { NSNumber(it.toInt()) }
    phoneNumber.numberOfLeadingZeros = numberOfLeadingZeros?.let { NSNumber(it) }
    phoneNumber.preferredDomesticCarrierCode = preferredDomesticCarrierCode
    phoneNumber.rawInput = rawInput
}
