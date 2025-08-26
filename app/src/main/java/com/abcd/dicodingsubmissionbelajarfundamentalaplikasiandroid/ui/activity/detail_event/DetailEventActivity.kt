package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.activity.detail_event

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.R
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.databinding.ActivityDetailEventBinding

@Suppress("DEPRECATION")
class DetailEventActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailEventBinding
    private var event: ListEventsModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        fetchPreviousData()
    }

    private fun fetchPreviousData(){
        val i = intent
        if(i != null){
            event = i.getParcelableExtra("list_event")
        }
    }

    private fun setToolbar(){
        binding.toolbar.apply {
            title = "Halaman Detail"
            setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}