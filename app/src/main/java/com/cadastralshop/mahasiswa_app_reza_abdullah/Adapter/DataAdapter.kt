package com.cadastralshop.mahasiswa_app_reza_abdullah.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cadastralshop.mahasiswa_app_reza_abdullah.Model.getdata.DataItem
import com.cadastralshop.mahasiswa_app_reza_abdullah.R
import kotlinx.android.synthetic.main.list_item.view.*

class DataAdapter (val data : List<DataItem>?, val itemClick : OnClickListener) : RecyclerView.Adapter<DataAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int) : ViewHolder{
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }


    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.nama.text = item?.mahasiswaNama
        holder.nohp.text = item?.mahasiswaNohp
        holder.alamat.text = item?.mahasiswaAlamat

        holder.view.setOnClickListener{
            itemClick.detail(item)
        }

        holder.btnhapus.setOnClickListener{
            itemClick.hapus(item)
        }

    }

    class  ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val nama = view.tvNama
        val nohp = view.tvNohp
        val alamat = view.tvAlamat
        val btnhapus = view.btnHapus


    }

    interface OnClickListener {
        fun detail(item: DataItem?)
        fun hapus(item: DataItem?)
    }

}