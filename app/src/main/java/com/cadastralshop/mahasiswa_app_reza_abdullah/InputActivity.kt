package com.cadastralshop.mahasiswa_app_reza_abdullah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cadastralshop.mahasiswa_app_reza_abdullah.Config.NetworkModule
import com.cadastralshop.mahasiswa_app_reza_abdullah.Model.action.ResponseAction
import com.cadastralshop.mahasiswa_app_reza_abdullah.Model.getdata.DataItem
import kotlinx.android.synthetic.main.activity_input.*
import kotlinx.android.synthetic.main.list_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val getData = intent.getParcelableExtra<DataItem>("data")

        if (getData != null) {
            etNama.setText(getData.mahasiswaNama)
            etNohp.setText(getData.mahasiswaNohp)
            etAlamat.setText(getData.mahasiswaAlamat)

            btnSimpan.text = "Update"

        }

        when (btnSimpan.text) {
            "Update" -> {
                btnSimpan.setOnClickListener {
                    updateData(
                        getData?.idMahasiswa,
                        etNama.text.toString(),
                        etNohp.text.toString(),
                        etAlamat.text.toString()
                    )
                }


            }
            else -> {
                btnSimpan.setOnClickListener {
                    inputData(
                        etNama.text.toString(),
                        etNohp.text.toString(),
                        etAlamat.text.toString()
                    )
                }
            }
        }



        btnBatal.setOnClickListener {
            finish()
        }

    }


    private fun inputData(nama: String?, nohp: String?, alamat: String?) {

        val input = NetworkModule.service().insertData(nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseAction> {


            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {

                Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT)
                    .show()
                finish()

            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }

        })
    }

    private fun updateData(id:String?, nama: String?, nohp: String?, alamat: String?) {

        val input = NetworkModule.service().updateData(id?: "", nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseAction> {


            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {

                Toast.makeText(applicationContext, "Data berhasil diperbarui", Toast.LENGTH_SHORT)
                    .show()
                finish()

            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()

            }

        })
    }
}