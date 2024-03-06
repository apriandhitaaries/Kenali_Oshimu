package com.waro.aku

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Oshi(
    val name: String,
    val description: String,
    val photo: Int,
    val jikoshoukai: String,
    val nickname: String,
    val birthday: String,
    val bloodType: String,
    val horoscope: String,
    val height: String,
    val fanbase: String,
    val instagram: String,
    val tiktok: String,
    val twitter: String,
    val share: String
) : Parcelable
