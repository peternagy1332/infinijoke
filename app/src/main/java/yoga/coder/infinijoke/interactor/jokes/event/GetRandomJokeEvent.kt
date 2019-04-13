package yoga.coder.infinijoke.interactor.jokes.event

import yoga.coder.infinijoke.model.Joke

data class GetRandomJokeEvent (
    var joke: Joke? = null
)