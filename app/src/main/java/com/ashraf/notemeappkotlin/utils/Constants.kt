package com.ashraf.notemeappkotlin.utils

import java.text.SimpleDateFormat
import java.util.*

public class Constants {
    companion object{
        val STATUS_OPEN = "Open"
        val STATUS_IN_PROGRESS = "In-Progress"
        val STATUS_TEST = "Test"
        val STATUS_DONE = "Done"

        var DATE_FORMAT_DD_MM_YYYY = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        var ddIMMIyyyyHHmmss = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")


        val EMAIL = "Email"
        val PHONE = "Phone"
        val URL = "Url"
    }

}