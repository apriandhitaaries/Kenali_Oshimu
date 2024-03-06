package com.waro.aku

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.waro.aku.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val key_oshi = "key_oshi"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataOshi = if (Build.VERSION.SDK_INT >= 34) {
            intent.getParcelableExtra<Oshi>("key_oshi", Oshi::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Oshi>(key_oshi)
        }

        if (dataOshi != null) {
            binding.tvDetailName.text = dataOshi.name
            binding.imgDetailPhoto.setImageResource(dataOshi.photo)
            binding.tvDetailJikoshoukai.text = dataOshi.jikoshoukai
            binding.tvDetailNickname.text = dataOshi.nickname
            binding.tvDetailBirthday.text = dataOshi.birthday
            binding.tvDetailBloodType.text = dataOshi.bloodType
            binding.tvDetailHoroscope.text = dataOshi.horoscope
            binding.tvDetailHeight.text = dataOshi.height
            binding.tvDetailFanbase.text = dataOshi.fanbase
            binding.btnInstagram.setOnClickListener {
                val instagramUrl = dataOshi.instagram
                val instagramAppUri = Uri.parse(instagramUrl)
                val intent = Intent(Intent.ACTION_VIEW, instagramAppUri)
                intent.setPackage("com.instagram.android")

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl)))
                }
            }
            binding.btnTiktok.setOnClickListener {
                val tiktokUrl = dataOshi.tiktok
                val tiktokAppUri = Uri.parse(tiktokUrl)
                val intent = Intent(Intent.ACTION_VIEW, tiktokAppUri)
                intent.setPackage("com.ss.android.ugc.trill")

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(tiktokUrl)))
                }
            }
            binding.btnTwitter.setOnClickListener {
                val twitterUrl = dataOshi.twitter
                val twitterAppUri = Uri.parse(twitterUrl)
                val intent = Intent(Intent.ACTION_VIEW, twitterAppUri)
                intent.setPackage("com.twitter.android")

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl)))
                }
            }
            binding.actionShare.setOnClickListener {
                val shareIntent = Intent().apply {
                    val shareUrl = dataOshi.share
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, shareUrl)
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"))
            }
        }

    }

}