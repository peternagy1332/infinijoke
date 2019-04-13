package yoga.coder.infinijoke.interactor

import dagger.Module
import dagger.Provides
import yoga.coder.infinijoke.interactor.jokes.JokesInteractor
import yoga.coder.infinijoke.network.JokesApi
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideJokesInteractor(jokesApi: JokesApi) = JokesInteractor(jokesApi)
}