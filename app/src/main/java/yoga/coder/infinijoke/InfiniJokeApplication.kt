package yoga.coder.infinijoke

import android.app.Application
import yoga.coder.infinijoke.ui.UIModule

class InfiniJokeApplication : Application() {
    lateinit var injector: InfiniJokeApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerInfiniJokeApplicationComponent.builder().uIModule(UIModule(this)).build()
    }
}