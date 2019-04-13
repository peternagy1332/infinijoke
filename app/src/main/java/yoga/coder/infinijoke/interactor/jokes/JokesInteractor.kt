package yoga.coder.infinijoke.interactor.jokes

import android.util.Log
import org.greenrobot.eventbus.EventBus
import yoga.coder.infinijoke.interactor.jokes.event.GetRandomJokeEvent
import yoga.coder.infinijoke.interactor.jokes.event.GetTenRandomJokesEvent
import yoga.coder.infinijoke.network.JokesApi
import javax.inject.Inject

class JokesInteractor @Inject constructor(private var jokesApi: JokesApi) {

    fun getRandomJoke() {
        val event = GetRandomJokeEvent()

        try {

            val jokeCall = jokesApi.getRandomJoke()

            val response = jokeCall.execute()
            Log.d("Reponse", response.body().toString())
            if (response.code() != 200) {
                throw Exception("Result code is not 200")
            }
            event.joke = response.body()
            EventBus.getDefault().post(event)
        } catch (e: Exception) {
            EventBus.getDefault().post(event)
        }
    }

    fun getTenRandomJokes() {
        val event = GetTenRandomJokesEvent()

        try {

            val jokeCall = jokesApi.getTenRandomJokes()

            val response = jokeCall.execute()
            Log.d("Reponse", response.body().toString())
            if (response.code() != 200) {
                throw Exception("Result code is not 200")
            }
            event.code = response.code()
            event.jokes = response.body()
            EventBus.getDefault().post(event)
        } catch (e: Exception) {
            EventBus.getDefault().post(event)
        }
    }
}