package co.gotoinc.ui.view.airports

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.gotoinc.core.entity.Airport
import co.gotoinc.ui.R
import kotlinx.android.synthetic.main.item_airport.view.*
import java.util.*

class AirportsAdapter(val onItemClick: (Airport) -> Unit) :
    PagedListAdapter<Airport, AirportsAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun submitList(pagedList: PagedList<Airport>?) {
        super.submitList(pagedList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_airport,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Airport) {
            view.run {
                iata.text = item.code
                country.text = Locale("en", item.countryIsoCode).displayCountry
                city.text = item.city
                setOnClickListener { onItemClick(item) }
            }
        }
    }

    companion object {

        @JvmStatic
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Airport>() {
            override fun areItemsTheSame(p0: Airport, p1: Airport) = p0.code == p1.code
            override fun areContentsTheSame(p0: Airport, p1: Airport) =
                p0 == p1
        }
    }
}