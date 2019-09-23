package com.example.opensolutionstestapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ValCurs(
    val Date: String,
    val name: String,
    val Valute: ArrayList<Valute>) : Parcelable