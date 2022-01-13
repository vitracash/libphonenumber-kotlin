package com.vitra.libphonenumber

expect class AsYouTypeFormatter {
    fun inputDigit(nextChar: Char): String
    fun clear()
}
