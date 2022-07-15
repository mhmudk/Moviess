package com.example.movies.RemoteDB.MoviesPopular

import android.util.Log
import androidx.lifecycle.MutableLiveData

import com.example.movies.Pojo.Movies.Movies
import com.example.movies.RemoteDB.Builder
import com.example.movies.RemoteDB.UserInterFace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesPopularRepo {

    fun getMovies(): MutableLiveData<Movies> {
        var mutableLiveData = MutableLiveData<Movies>()
        var userInterfacebuilder = Builder.retorfitBuilder.create(UserInterFace::class.java)
        var call = userInterfacebuilder.getPopularMovies()
        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    mutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("Error", t.message.toString())

            }
        })
        return mutableLiveData
    }
}