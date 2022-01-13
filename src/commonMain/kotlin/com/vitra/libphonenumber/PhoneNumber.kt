package com.vitra.libphonenumber

data class PhoneNumber(
    val countryCode: Int? = null,
    val extension: String? = null,
    val hasItalianLeadingZero: Boolean = false,
    val nationalNumber: Long? = null,
    val numberOfLeadingZeros: Int? = null,
    val preferredDomesticCarrierCode: String? = null,
    val rawInput: String? = null
)
