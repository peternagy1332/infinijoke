package yoga.coder.infinijoke

import android.app.Application

class InfiniJokeApplication : Application() {
    lateinit var injector: KotifyDemoApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerKotifyDemoApplicationComponent.builder().uIModule(UIModule(this)).build()
    }
}