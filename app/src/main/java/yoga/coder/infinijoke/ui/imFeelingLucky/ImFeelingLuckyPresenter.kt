package yoga.coder.infinijoke.ui.imFeelingLucky

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import yoga.coder.infinijoke.interactor.jokes.JokesInteractor
import yoga.coder.infinijoke.interactor.jokes.event.GetRandomJokeEvent
import yoga.coder.infinijoke.interactor.jokes.event.GetTenRandomJokesEvent
import yoga.coder.infinijoke.model.Joke
import yoga.coder.infinijoke.ui.Presenter
import yoga.coder.infinijoke.ui.infiniteJokes.InfiniteJokesScreen
import java.util.concurrent.Executor
import javax.inject.Inject

class ImFeelingLuckyPresenter @Inject constructor(
    private val executor: Executor,
    private val jokesInteractor: JokesInteractor
):
    Presenter<ImFeelingLuckyScreen>() {

    override fun attachScreen(screen: ImFeelingLuckyScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun requestRandomJoke(numOfCards: Int = 1) {
        executor.execute {
            for(i in 1..numOfCards) {
                jokesInteractor.getRandomJoke()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetRandomJokeEvent) {
//        if (event.throwable != null) {
//            event.throwable?.printStackTrace()
//            if (screen != null) {
//                screen?.showNetworkError(event.throwable?.message.orEmpty())
//            }
//        } else {
        if (screen != null) {
            if (event.joke != null) {
                event.joke?.let {
                    screen?.addRandomJoke(it)
                }
            }
        }
//        }
    }
}