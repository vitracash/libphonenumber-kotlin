package com.vitra.libphonenumber

import com.google.i18n.phonenumbers.AsYouTypeFormatter as NativeFormatter

actual class AsYouTypeFormatter internal constructor(private val formatter: NativeFormatter) {
    actual fun inputDigit(nextChar: Char) = formatter.inputDigit(nextChar)
    actual fun clear() = formatter.clear()
}
