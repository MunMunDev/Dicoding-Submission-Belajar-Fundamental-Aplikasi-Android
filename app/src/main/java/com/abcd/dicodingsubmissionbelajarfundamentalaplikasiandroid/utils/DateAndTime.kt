package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils

import android.annotation.SuppressLint

@SuppressLint("SimpleDateFormat")
class DateAndTime {
    private fun konversiBulan(bulan: String): String{
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

    private fun waktuNoSecond(waktu: String): String{
        val arrayWaktu = waktu.split(":")
        return "${arrayWaktu[0]}:${arrayWaktu[1]}"
    }

    fun konversiBulanDanWaktu(tanggalDanWaktu: String): String{
        val splitTanggalDanWaktu = tanggalDanWaktu.split(" ")
        val tanggal = konversiBulan(splitTanggalDanWaktu[0])
        val waktu = waktuNoSecond(splitTanggalDanWaktu[1])

        return "$tanggal $waktu"
    }

}