package co.gotoinc.ui.base

import androidx.recyclerview.widget.RecyclerView
import co.gotoinc.core.ListItem

abstract class BaseAdapter<ViewHolder : RecyclerView.ViewHolder, T: ListItem> :
    RecyclerView.Adapter<ViewHolder>() {
    var list: List<T>? = null
    fun submitList(list: List<T>) {
        this@BaseAdapter.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = list?.size ?: 0
}