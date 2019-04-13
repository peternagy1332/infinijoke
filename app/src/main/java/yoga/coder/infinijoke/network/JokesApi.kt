package yoga.coder.infinijoke.network

import retrofit2.Call
import retrofit2.http.GET
import yoga.coder.infinijoke.model.Joke

interface JokesApi {
    @GET("jokes/random")
    fun getRandomJoke(): Call<Joke>

    @GET("jokes/ten")
    fun getTenRandomJokes(): Call<List<Joke>>
}