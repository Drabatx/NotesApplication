package com.example.notesapplication.utils

import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UiUtils {
    companion object{
        fun bindFormattedDate(textView: TextView, timestampMillis: Long) {
            val dateFormat = SimpleDateFormat("dd/MMM/yyyy HH:mm:ss", Locale.getDefault())
            val formattedDate = dateFormat.format(Date(timestampMillis))
            textView.text = formattedDate
        }
    }
}