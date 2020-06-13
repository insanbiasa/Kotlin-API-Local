package com.cadastralshop.mahasiswa_app_reza_abdullah.Config

import com.cadastralshop.mahasiswa_app_reza_abdullah.Model.action.ResponseAction
import com.cadastralshop.mahasiswa_app_reza_abdullah.Model.getdata.ResponseGetData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //getData
    @GET("getData.php")
    fun getData() : Call<ResponseGetData>

    //getDataById
    @GET("getData.php")
    fun getDataById(@Query("id_mahasiswa") id: String) : Call<ResponseGetData>

    //insert
    @FormUrlEncoded
    @POST("insert.php")
    fun insertData(@Field("mahasiswa_nama") nama : String,
                   @Field("mahasiswa_nohp") nohp : String,
                   @Field("mahasiswa_alamat") alamat : String): Call<ResponseAction>

    //update
    @FormUrlEncoded
    @POST("update.php")
    fun updateData (@Field("id_mahasiswa") id : String,
                    @Field("mahasiswa_nama") nama : String,
                   @Field("mahasiswa_nohp") nohp : String,
                   @Field("mahasiswa_alamat") alamat : String): Call<ResponseAction>

    //delete
    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData (@Field("id_mahasiswa") id : String) : Call<ResponseAction>
}