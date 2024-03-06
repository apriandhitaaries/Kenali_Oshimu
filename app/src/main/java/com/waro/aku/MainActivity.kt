package com.waro.aku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.waro.aku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvOshi: RecyclerView
    private val list = ArrayList<Oshi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvOshi = binding.rvOshi
        rvOshi.setHasFixedSize(true)

        list.addAll(getListOshi())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_about -> {
                val intentAbout = Intent(this@MainActivity, About::class.java)
                startActivity(intentAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getListOshi(): ArrayList<Oshi>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataJikoshoukai = resources.getStringArray(R.array.data_jikoshoukai)
        val dataNickname = resources.getStringArray(R.array.data_nickname)
        val dataBirthday = resources.getStringArray(R.array.data_birthday)
        val dataBloodType = resources.getStringArray(R.array.data_blood_type)
        val dataHoroscope = resources.getStringArray(R.array.data_horoscope)
        val dataHeight = resources.getStringArray(R.array.data_height)
        val dataFanbase = resources.getStringArray(R.array.data_fanbase)
        val dataInstagram = resources.getStringArray(R.array.data_instagram)
        val dataTiktok = resources.getStringArray(R.array.data_tiktok)
        val dataTwitter = resources.getStringArray(R.array.data_twitter)
        val dataShare = resources.getStringArray(R.array.data_share)

        val listOshi = ArrayList<Oshi>()

        for (i in dataName.indices){
            val oshi = Oshi(
                dataName[i],
                dataDesc[i],
                dataPhoto.getResourceId(i, -1),
                dataJikoshoukai[i],
                dataNickname[i],
                dataBirthday[i],
                dataBloodType[i],
                dataHoroscope[i],
                dataHeight[i],
                dataFanbase[i],
                dataInstagram[i],
                dataTiktok[i],
                dataTwitter[i],
                dataShare[i]
            )
            listOshi.add(oshi)
        }
        return listOshi
    }

    private fun showRecyclerList(){
        rvOshi.layoutManager = LinearLayoutManager(this)
        val listOshiAdapter = ListOshiAdapter(list)
        rvOshi.adapter = listOshiAdapter

        listOshiAdapter.setOnItemClickCallback(object : ListOshiAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Oshi) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("key_oshi", data)
                startActivity(intent)
            }
        })
    }
}