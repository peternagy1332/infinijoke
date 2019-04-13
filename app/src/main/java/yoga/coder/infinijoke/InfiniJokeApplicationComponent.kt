package yoga.coder.infinijoke

import dagger.Component
import dagger.Provides
import yoga.coder.infinijoke.interactor.InteractorModule
import yoga.coder.infinijoke.network.NetworkModule
import yoga.coder.infinijoke.ui.UIModule
import yoga.coder.infinijoke.ui.imFeelingLucky.ImFeelingLuckyFragment
import yoga.coder.infinijoke.ui.infiniteJokes.InfiniteJokesFragment
import yoga.coder.infinijoke.ui.main.MainActivity
import yoga.coder.infinijoke.ui.savedJokes.SavedJokesFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, NetworkModule::class, InteractorModule::class])
interface InfiniJokeApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(infiniteJokesFragment: InfiniteJokesFragment)
    fun inject(imFeelingLuckyFragment: ImFeelingLuckyFragment)
    fun inject(savedJokesFragment: SavedJokesFragment)
}