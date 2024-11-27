package com.peterchege.dummyproject.core.util

import android.content.Context
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import java.util.Random

fun String.isSpecialCharString(): Boolean {
    return all { !it.isLetterOrDigit() }
}


fun getTodaysDate(): String {
    val formatter = java.text.SimpleDateFormat("dd/MM/yyyy")
    val date = java.util.Date()
    return formatter.format(date)
}


fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun randomColorCode(): String {
    val random = Random()
    val nextInt = random.nextInt(0xffffff + 1)
    return String.format("#%06x", nextInt).drop(1).capitalize(Locale.ROOT)
}

fun generateAvatarURL(name:String):String{
    val splitname = name.split(" ").joinToString("+")
    val color = randomColorCode()
    return "https://ui-avatars.com/api/?background=${color}&color=fff&name=${splitname}&bold=true&fontsize=0.6&rounded=true"

}


fun formatCurrency(amount: Double, currencyCode: String): String {
    val currency = Currency.getInstance(currencyCode)
    //val format = NumberFormat.getCurrencyInstance(Locale.getDefault())

    val format = NumberFormat.getCurrencyInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }

    format.currency = currency
    val curr = format.format(amount)
    var formattedCurr = ""
    var formattedAmount = ""
    when (currency.toString()) {
        "USD" -> {
            if (amount >= 0.0) {
                formattedCurr = currency.toString()
                formattedAmount = extractAmount(curr.toString()).toString()
            } else {
                formattedCurr = currency.toString()
                formattedAmount = "-" + extractAmount(curr).toString()
            }

        }

        else -> {
            if (curr.startsWith("-")) {
                formattedCurr = curr.substring(startIndex = 1, endIndex = 4)
                formattedAmount = "-" + extractAmount(curr).toString()
            } else {
                formattedCurr = curr.substring(startIndex = 0, endIndex = 3)
                formattedAmount = extractAmount(curr).toString()
            }
        }
    }

    //val x = String.format("%.2f", formattedAmount).toDouble()
    return "$formattedAmount $formattedCurr"
    // return "$x $formattedCurr"

}
private fun extractAmount(input: String): String? {
    val regex = Regex("[\\d,.]+")
    val matchResult = regex.find(input)
    return matchResult?.value
}

fun String?.safeToDouble(): Double {
    val myDouble = this?.trim()?.toDoubleOrNull() ?: 0.00
    return BigDecimal(myDouble).setScale(2, RoundingMode.HALF_UP).toDouble()
}


