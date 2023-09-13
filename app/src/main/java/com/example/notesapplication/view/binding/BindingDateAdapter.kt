package com.example.notesapplication.view.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@BindingAdapter("formattedDate")
fun bindFormattedDate(textView: TextView, timestampMillis: Long) {
    val dateFormat = SimpleDateFormat("dd/MMM/yyyy HH:mm:ss", Locale.getDefault())
    val formattedDate = dateFormat.format(Date(timestampMillis))
    textView.text = formattedDate
}