package yoga.coder.infinijoke.ui.savedJokes

import yoga.coder.infinijoke.interactor.jokes.JokesInteractor
import yoga.coder.infinijoke.ui.Presenter
import java.util.concurrent.Executor
import javax.inject.Inject

class SavedJokesPresenter @Inject constructor(
    private val executor: Executor,
    private val jokesInteractor: JokesInteractor
):
    Presenter<SavedJokesScreen>() {

}