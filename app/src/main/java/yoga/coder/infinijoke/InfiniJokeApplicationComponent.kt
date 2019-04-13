package yoga.coder.infinijoke

import dagger.Component
import yoga.coder.infinijoke.interactor.InteractorModule
import yoga.coder.infinijoke.network.NetworkModule
import yoga.coder.infinijoke.ui.UIModule
import yoga.coder.infinijoke.ui.infiniteJokes.InfiniteJokesFragment
import yoga.coder.infinijoke.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, NetworkModule::class, InteractorModule::class])
interface InfiniJokeApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(infiniteJokesFragment: InfiniteJokesFragment)
}