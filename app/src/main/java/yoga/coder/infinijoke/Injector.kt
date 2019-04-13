package yoga.coder.infinijoke

import android.app.Activity
import android.support.v4.app.Fragment

val Activity.injector: InfiniJokeApplicationComponent
    get() {
        return (this.applicationContext as InfiniJokeApplication).injector
    }

val Fragment.injector: InfiniJokeApplicationComponent
    get() {
        return (this.context!!.applicationContext as InfiniJokeApplication).injector
    }