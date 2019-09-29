package com.example.omdb.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.omdb.R
import com.example.omdb.models.Episode

class EpisodiosAdapter(var context: Context, var lista: List<Episode>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var view = inflater.inflate(R.layout.item_epsodio, null)

        var title = view.findViewById<TextView>(R.id.title)
        title.text = lista[position].Title
        var rating = view.findViewById<TextView>(R.id.rating)
        rating.text = lista[position].imdbRating

        return view

    }

    override fun getItem(position: Int): Any {
        return ""
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return lista.size
    }
}