package yoga.coder.infinijoke.ui

import android.content.Context
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun mainPresenter() = MainPresenter()

    @Provides
    @Singleton
    fun jokePresenter(executor: Executor, jokeInteractor: JokeInteractor) = JokePresenter(executor, jokeInteractor)

    @Provides
    @Singleton
    fun networkExecutor(): Executor = Executors.newFixedThreadPool(1)
}