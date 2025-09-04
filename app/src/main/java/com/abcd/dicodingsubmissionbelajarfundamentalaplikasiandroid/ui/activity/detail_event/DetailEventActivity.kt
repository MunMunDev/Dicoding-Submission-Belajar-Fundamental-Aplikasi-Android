package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.activity.detail_event

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.R
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.databinding.ActivityDetailEventBinding
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.DateAndTime
import com.bumptech.glide.Glide
import androidx.core.net.toUri
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class DetailEventActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailEventBinding
    private var event: ListEventsModel? = null
    private val dateAndTime = DateAndTime()
    private val viewModel: CrudFavoriteViewModel by viewModels()
    private var checkActiveFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStartShimmer()
        setToolbar()
        fetchPreviousData()
        isFavorite()
        insertFavoriteEvent()
        deleteFavoriteEvent()
        setButton()
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun setButton() {
        binding.apply {
            btnRegister.setOnClickListener {
                event?.link?.let { link ->
                    val uri = link.toUri()
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }

            btnFavorite.setOnClickListener {
                event?.let { mEvent ->
                    if(!checkActiveFavorite)
                        viewModel.insertFavoriteEvents(mEvent)
                    else
                        viewModel.deleteFavoriteEvents(mEvent)
                }
            }
        }
    }

    private fun insertFavoriteEvent(){
        viewModel.insertFavorite.observe(this@DetailEventActivity){result->
            Log.d("DetailTAG", "insertFavoriteEvent: $result")
            if(result != -1L){
                setFavoriteActivate()
                checkActiveFavorite = true
            }
        }
    }

    private fun deleteFavoriteEvent(){
        viewModel.deleteFavorite.observe(this@DetailEventActivity){result->
            if(result == 1){
                setFavoriteDeactivate()
                checkActiveFavorite = false
            }
        }
    }

    private fun fetchPreviousData(){
        val i = intent
        if(i != null){
            event = i.getParcelableExtra("list_event")
            event?.id?.let { nId ->
                viewModel.searchEventsFavorite(nId)
            }
            setDataEvent(event)
        }
    }

    private fun isFavorite() {
        viewModel.isFavorite.observe(this@DetailEventActivity){result->
            binding.apply {
                checkActiveFavorite = result
                Log.d("DetailTAG", "isFavorite: $result")

                if(result)
                    setFavoriteActivate()
                else
                    setFavoriteDeactivate()
            }
        }
    }

    private fun setFavoriteActivate(){
        binding.btnFavorite.setImageResource(R.drawable.baseline_favorite_24_active)
    }

    private fun setFavoriteDeactivate(){
        binding.btnFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
    }

    private fun setToolbar(){
        binding.toolbar.apply {
            title = "Halaman Detail"
            setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    @SuppressLint("SetTextI18n", "SetJavaScriptEnabled")
    private fun setDataEvent(event: ListEventsModel?) {
        setStopShimmer()
        event?.let {
            with(it){
                val tglMulaiPelaksanaan = dateAndTime.konversiBulanDanWaktu(beginTime!!)
                val nameEvent = name
                val summaryEvent = summary
                val ownerNameEvent = ownerName
                val quotaEvent = quota-registrants
                val cityNameEvent = cityName
                val categoryEvent = category
                val descriptionEvent = description

                with(binding) {
                    tvNameEvent.text = nameEvent
                    tvSummary.text = summaryEvent
                    tvPenyelenggaraEvent.text = ownerNameEvent
                    tvKuota.text = "$quotaEvent"
                    tvPelaksanaan.text = tglMulaiPelaksanaan
                    tvLokasi.text = cityNameEvent
                    tvKategori.text = categoryEvent
                    
                    val tempDescription = Html.fromHtml(descriptionEvent)
                    tvDeskripsi.text = tempDescription

                    Glide.with(this@DetailEventActivity)
                        .load(imageLogo) // URL Gambar
                        .error(R.drawable.image_error)
                        .into(ivGambarEvent) // imageView mana yang akan diterapkan

                    wvDescription.settings.javaScriptEnabled = true

                    val htmlData = """
                        <style>
                            body {
                                margin: 0;
                                padding: 16px;
                                font-family: sans-serif;
                                overflow-x: hidden; /* cegah scroll horizontal */
                            }
                            img {
                                max-width: 100% !important; /* gambar mengikuti lebar layar */
                                height: auto !important;    /* tinggi proporsional */
                                display: block;
                            }
                            a {
                                color: #1565c0;  /* warna link */
                                text-decoration: none;
                            }
                        </style>
                        $descriptionEvent
                    """.trimIndent()

                    wvDescription.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null)
                }
            }
        }
    }

    private fun setStartShimmer(){
        with(binding){
            smImage.startShimmer()
            smPenjelasan.startShimmer()

            smImage.visibility = View.VISIBLE
            smPenjelasan.visibility = View.VISIBLE

            ivGambarEvent.visibility = View.GONE
            clPenjelasan.visibility = View.GONE
        }
    }

    private fun setStopShimmer(){
        with(binding){
            smImage.stopShimmer()
            smPenjelasan.stopShimmer()

            smImage.visibility = View.GONE
            smPenjelasan.visibility = View.GONE

            ivGambarEvent.visibility = View.VISIBLE
            clPenjelasan.visibility = View.VISIBLE
        }
    }
}