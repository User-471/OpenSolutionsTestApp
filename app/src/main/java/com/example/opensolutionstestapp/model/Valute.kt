package com.example.opensolutionstestapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Valute(
    val ID: String,
    val NumCode: String,
    val CharCode: String,
    val Nominal: String,
    val Name: String,
    val Value: String) : Parcelable