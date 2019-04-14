package yoga.coder.infinijoke.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import yoga.coder.infinijoke.AppDatabase
import yoga.coder.infinijoke.model.Joke
import yoga.coder.infinijoke.repository.JokeRepository
import kotlin.coroutines.CoroutineContext

class JokeViewModel(application: Application): AndroidViewModel(application) {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: JokeRepository
    val savedJokes: LiveData<List<Joke>>
    val jokeTypes: LiveData<List<String>>

    init {
        val jokeDao = AppDatabase.getDatabase(application).jokeDao()
        repository = JokeRepository(jokeDao)
        savedJokes = repository.allJokes
        jokeTypes = repository.allTypes
    }

    fun insert(joke: Joke) = scope.launch(Dispatchers.IO) {
        repository.insert(joke)
    }

    fun delete(joke: Joke) = scope.launch(Dispatchers.IO) {
        repository.delete(joke)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}