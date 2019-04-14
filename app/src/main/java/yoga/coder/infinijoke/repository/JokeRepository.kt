package yoga.coder.infinijoke.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import yoga.coder.infinijoke.dao.JokeDao
import yoga.coder.infinijoke.model.Joke

class JokeRepository(private val jokeDao: JokeDao) {
    val allJokes: LiveData<List<Joke>> = jokeDao.getAll()
    val allTypes: LiveData<List<String>> = jokeDao.getAllTypes()

    @WorkerThread
    suspend fun insert(joke: Joke) {
        jokeDao.insert(joke)
    }

    @WorkerThread
    suspend fun delete(joke: Joke) {
        jokeDao.delete(joke)
    }
}