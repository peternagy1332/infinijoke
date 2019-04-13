package yoga.coder.infinijoke.ui

import android.content.Context
import dagger.Module
import dagger.Provides
import yoga.coder.infinijoke.ui.main.MainPresenter
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun mainPresenter() = MainPresenter()

//    @Provides
//    @Singleton
//    fun jokePresenter(executor: Executor, jokeInteractor: JokesInteractor) = JokesPresenter(executor, jokeInteractor)

    @Provides
    @Singleton
    fun networkExecutor(): ExecutorService? = Executors.newFixedThreadPool(1)
}