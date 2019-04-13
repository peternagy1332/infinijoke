package yoga.coder.infinijoke.ui.infiniteJokes

import yoga.coder.infinijoke.model.Joke

interface InfiniteJokesScreen {
    fun showJokes(jokes: MutableList<Joke>?)
//    fun showNetworkError(errorMsg: String)
}