package com.cadastralshop.mahasiswa_app_reza_abdullah.Model.getdata

import com.google.gson.annotations.SerializedName

data class ResponseGetData(

	@field:SerializedName("data")
	val data: List<DataItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)

