package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.widget.TextView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@SuppressLint("SimpleDateFormat")
class DateAndTime {
    fun konversiBulan(bulan: String): String{
        val arrayBulan = arrayOf(
            "Januari",
            "Februari",
            "Maret",
            "April",
            "Mei",
            "Juni",
            "Juli",
            "Agustus",
            "September",
            "Oktober",
            "November",
            "Desember"
        )

        val splitBulan = bulan.split("-")
        val valueBulan = "${splitBulan[2]} ${arrayBulan[(splitBulan[1].toInt()-1)]} ${splitBulan[0]}"

        return valueBulan

    }
    fun konversiBulanSingkatan(bulan: String): String{
        val arrayBulan = arrayOf(
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "Mei",
            "Juni",
            "Juli",
            "Agust",
            "Sep",
            "Okt",
            "Nov",
            "Des"
        )

        val splitBulan = bulan.split("-")
        val valueBulan = "${splitBulan[2]} ${arrayBulan[(splitBulan[1].toInt()-1)]} ${splitBulan[0]}"

        return valueBulan
    }

    fun waktuNoSecond(waktu: String): String{
        val arrayWaktu = waktu.split(":")
        return "${arrayWaktu[0]}:${arrayWaktu[1]}"
    }

    fun konversiBulanDanWaktu(tanggalDanWaktu: String): String{
        val splitTanggalDanWaktu = tanggalDanWaktu.split(" ")
        val tanggal = konversiBulan(splitTanggalDanWaktu[0])
        val waktu = waktuNoSecond(splitTanggalDanWaktu[1])

        return "$tanggal $waktu"
    }

    fun konversiBulanSingkatanDanWaktu(tanggalDanWaktu: String): String{
        val splitTanggalDanWaktu = tanggalDanWaktu.split(" ")
        val tanggal = konversiBulanSingkatan(splitTanggalDanWaktu[0])
        val waktu = waktuNoSecond(splitTanggalDanWaktu[1])

        return "$tanggal $waktu"
    }

}