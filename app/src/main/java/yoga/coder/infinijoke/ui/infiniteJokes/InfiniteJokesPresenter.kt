package yoga.coder.infinijoke.ui.infiniteJokes

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import yoga.coder.infinijoke.interactor.jokes.JokesInteractor
import yoga.coder.infinijoke.interactor.jokes.event.GetTenRandomJokesEvent
import yoga.coder.infinijoke.model.Joke
import yoga.coder.infinijoke.ui.Presenter
import java.util.concurrent.Executor
import javax.inject.Inject

class InfiniteJokesPresenter @Inject constructor(
    private val executor: Executor,
    private val jokesInteractor: JokesInteractor):
    Presenter<InfiniteJokesScreen>() {

    override fun attachScreen(screen: InfiniteJokesScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun requestTenRandomJokes() {
        executor.execute {
            jokesInteractor.getTenRandomJokes()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetTenRandomJokesEvent) {
//        if (event.throwable != null) {
//            event.throwable?.printStackTrace()
//            if (screen != null) {
//                screen?.showNetworkError(event.throwable?.message.orEmpty())
//            }
//        } else {
            if (screen != null) {
                if (event.jokes != null) {
                    screen?.showJokes(event.jokes as MutableList<Joke>)
                }
            }
//        }
    }

}