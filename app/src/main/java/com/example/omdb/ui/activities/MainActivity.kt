package com.example.omdb.ui.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import com.bumptech.glide.Glide
import com.example.omdb.R
import com.example.omdb.models.Serie
import com.example.omdb.services.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnbuscar.setOnClickListener {
            searchSerie()
        }

        btnepsodios.setOnClickListener {
            var intent = Intent(this, EpisodiosActivity::class.java)
            intent.putExtra("busca", busca.text.toString())
            startActivity(intent)
        }
    }

    fun searchSerie() {

        var s = RetrofitInitializer().serviceOmdb()

        var call = s.searchSerie(busca.text.toString(), "2f5cfd66")

        call.enqueue(object : retrofit2.Callback<Serie> {
            override fun onResponse(call: Call<Serie>?, response: Response<Serie>?) {
                response?.let {
                    if (it.code() == 200) {

                        if (it!!.body().Response.equals("True")) {

                            titleserie.text = it!!.body().Title
                            genreserie.text = it!!.body().Genre
                            ratingserie.text = it!!.body().imdbRating
                            votesserie.text = it!!.body().imdbVotes

                            Glide
                                .with(this@MainActivity)
                                .load(it!!.body().Poster)
                                .into(posterserie)

                        } else {
                            MaterialDialog.Builder(this@MainActivity)
                                .theme(Theme.LIGHT)
                                .title(R.string.ops)
                                .content(R.string.mensagem)
                                .positiveText(R.string.ok)
                                .show()
                        }

                    }
                }
            }

            override fun onFailure(call: Call<Serie>?, t: Throwable?) {
                Log.i("Error", t.toString())
            }
        })

    }
}
