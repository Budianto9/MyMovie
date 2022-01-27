package com.example.mymovie.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : RecyclerView.ViewHolder?, U> : RecyclerView.Adapter<T>() {

    private var _itemList: MutableList<U> = mutableListOf()

    val itemList: List<U> = _itemList

    fun initNewItems(list: List<U>) {
        _itemList.clear()
        _itemList.addAll(list)
        notifyDataSetChanged()
    }

    fun addAllItems(list: List<U>) {
        _itemList.addAll(list)
        notifyDataSetChanged()
    }


}