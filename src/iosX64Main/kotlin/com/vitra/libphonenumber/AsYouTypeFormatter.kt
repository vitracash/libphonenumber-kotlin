package com.vitra.libphonenumber

import cocoapods.libPhoneNumber_iOS.NBAsYouTypeFormatter

actual class AsYouTypeFormatter internal constructor(private val formatter: NBAsYouTypeFormatter) {
    actual fun inputDigit(nextChar: Char) = formatter.inputDigit(nextChar.toString())!!
    actual fun clear() = formatter.clear()
}
