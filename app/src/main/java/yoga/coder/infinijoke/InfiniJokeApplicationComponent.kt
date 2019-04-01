package yoga.coder.infinijoke

import dagger.Component
import yoga.coder.infinijoke.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, NetworkModule::class, InteractorModule::class])
interface InfiniJokeApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(artistsFragment: ArtistsFragment)
}