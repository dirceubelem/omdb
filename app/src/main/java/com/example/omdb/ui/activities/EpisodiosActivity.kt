package com.example.omdb.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.Theme
import com.example.omdb.R
import com.example.omdb.models.ResponseEpisodes
import com.example.omdb.services.RetrofitInitializer
import com.example.omdb.ui.adapters.EpisodiosAdapter
import kotlinx.android.synthetic.main.activity_episodios.*
import retrofit2.Call
import retrofit2.Response

class EpisodiosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episodios)

        searchSerie()
    }

    fun searchSerie() {

        var s = RetrofitInitializer().serviceOmdb()

        var call = s.searchSeason(intent.getStringExtra("busca"), "1", "2f5cfd66")

        call.enqueue(object : retrofit2.Callback<ResponseEpisodes> {
            override fun onResponse(call: Call<ResponseEpisodes>?, response: Response<ResponseEpisodes>?) {
                response?.let {
                    if (it.code() == 200) {

                        if (it!!.body().Response.equals("True")) {

                            lista.adapter = EpisodiosAdapter(this@EpisodiosActivity, it!!.body().Episodes)

                        } else {
                            MaterialDialog.Builder(this@EpisodiosActivity)
                                .theme(Theme.LIGHT)
                                .title(R.string.ops)
                                .content(R.string.mensagem)
                                .positiveText(R.string.ok)
                                .show()
                        }

                    }
                }
            }

            override fun onFailure(call: Call<ResponseEpisodes>?, t: Throwable?) {

            }
        })

    }
}
