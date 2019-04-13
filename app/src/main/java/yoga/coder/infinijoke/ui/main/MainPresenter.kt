package yoga.coder.infinijoke.ui.main

import yoga.coder.infinijoke.ui.Presenter
import yoga.coder.infinijoke.ui.imFeelingLucky.ImFeelingLuckyFragment
import yoga.coder.infinijoke.ui.infiniteJokes.InfiniteJokesFragment
import yoga.coder.infinijoke.ui.savedJokes.SavedJokesFragment

class MainPresenter: Presenter<MainScreen>() {
    override fun attachScreen(screen: MainScreen) {
        super.attachScreen(screen)
    }

    override fun detachScreen() {
        super.detachScreen()
    }

    fun presentInfiniteJokes() {
        screen!!.showFragment(InfiniteJokesFragment())
    }

    fun presentSavedJokes() {
        screen!!.showFragment(SavedJokesFragment())
    }

    fun presentImFeelingLucky() {
        screen!!.showFragment(ImFeelingLuckyFragment())
    }
}