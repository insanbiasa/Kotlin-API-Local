package com.cadastralshop.mahasiswa_app_reza_abdullah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.cadastralshop.mahasiswa_app_reza_abdullah.Adapter.DataAdapter
import com.cadastralshop.mahasiswa_app_reza_abdullah.Config.NetworkModule
import com.cadastralshop.mahasiswa_app_reza_abdullah.Model.action.ResponseAction
import com.cadastralshop.mahasiswa_app_reza_abdullah.Model.getdata.DataItem
import com.cadastralshop.mahasiswa_app_reza_abdullah.Model.getdata.ResponseGetData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener{
            val intent = Intent(
                this, InputActivity::class.java)
                startActivity(intent)
        }

        showData()

    }

    private fun showData() {

        val listMahasiswa = NetworkModule.service().getData()
        listMahasiswa.enqueue(object  : Callback<ResponseGetData> {


            override fun onResponse(
                call: Call<ResponseGetData>,
                response: Response<ResponseGetData>
            ) {

                if (response.isSuccessful){

                    val item = response.body()?.data

                    val adapter = DataAdapter(item, object : DataAdapter.OnClickListener{
                        override fun detail(item: DataItem?) {

                            val intent = Intent(applicationContext, InputActivity::class.java)
                            intent.putExtra("data", item)
                            startActivity(intent)

                        }

                        override fun hapus(item: DataItem?) {

                            AlertDialog.Builder(this@MainActivity).apply {
                                setTitle("Hapus Data")
                                setMessage("Apakah Anda Yakin untuk Menghapus?")
                                setPositiveButton("Hapus") {
                                    dialog, which ->
                                    hapusData(item?.idMahasiswa)
                                }

                                setNegativeButton("Batal") {
                                    dialog, which ->
                                    dialog.dismiss()
                                }
                            }.show()

                        }

                    })

                    list.adapter = adapter

                }

            }

            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun hapusData(idMahasiswa: String?) {
        val hapus = NetworkModule.service().deleteData(idMahasiswa?:"")
        hapus.enqueue(object : Callback<ResponseAction>{

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {

                Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                showData()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        showData()
    }




}