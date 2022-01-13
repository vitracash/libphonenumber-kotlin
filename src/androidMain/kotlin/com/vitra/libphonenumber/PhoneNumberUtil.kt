package com.vitra.libphonenumber

import com.google.i18n.phonenumbers.PhoneNumberUtil as NativePhoneNumberUtil
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat as NativePhoneNumberFormat
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber as NativePhoneNumber

actual class PhoneNumberUtil {
    private val nativeInstance = NativePhoneNumberUtil.getInstance()

    actual fun isValidNumber(phoneNumber: PhoneNumber): Boolean =
        nativeInstance.isValidNumber(phoneNumber.asNative())

    actual fun parse(phoneNumber: String, defaultRegion: String) =
        nativeInstance.parse(phoneNumber, defaultRegion).asKotlin()

    actual fun format(phoneNumber: PhoneNumber, format: PhoneNumberFormat) =
        nativeInstance.format(phoneNumber.asNative(), NativePhoneNumberFormat.valueOf(format.name))!!

    actual fun getAsYouTypeFormatter(regionCode: String) =
        AsYouTypeFormatter(nativeInstance.getAsYouTypeFormatter(regionCode))

    actual companion object {
        actual fun getInstance() = PhoneNumberUtil()
    }
}

private fun NativePhoneNumber.asKotlin(): PhoneNumber {
    return PhoneNumber(
        countryCode = if (hasCountryCode()) countryCode else null,
        extension = if (hasExtension()) extension else null,
        hasItalianLeadingZero = hasItalianLeadingZero(),
        nationalNumber = if (hasNationalNumber()) nationalNumber else null,
        numberOfLeadingZeros = if (hasNumberOfLeadingZeros()) numberOfLeadingZeros else null,
        preferredDomesticCarrierCode = if (hasPreferredDomesticCarrierCode()) preferredDomesticCarrierCode else null,
        rawInput = if (hasRawInput()) rawInput else null
    )
}

private fun PhoneNumber.asNative(): NativePhoneNumber {
    var phoneNumber = NativePhoneNumber()
    countryCode?.let { phoneNumber = phoneNumber.setCountryCode(it) }
    extension?.let { phoneNumber = phoneNumber.setExtension(it) }
    nationalNumber?.let { phoneNumber = phoneNumber.setNationalNumber(it) }
    numberOfLeadingZeros?.let { phoneNumber = phoneNumber.setNumberOfLeadingZeros(it) }
    preferredDomesticCarrierCode?.let { phoneNumber = phoneNumber.setPreferredDomesticCarrierCode(it) }
    rawInput?.let { phoneNumber = phoneNumber.setRawInput(it) }

    phoneNumber = phoneNumber.setItalianLeadingZero(hasItalianLeadingZero)
    return phoneNumber
}
