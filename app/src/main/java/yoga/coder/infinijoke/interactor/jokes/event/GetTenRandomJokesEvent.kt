package yoga.coder.infinijoke.interactor.jokes.event

import yoga.coder.infinijoke.model.Joke

data class GetTenRandomJokesEvent(
    var code: Int = 0,
    var jokes: List<Joke>? = null
)