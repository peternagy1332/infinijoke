package yoga.coder.infinijoke.model

import com.google.gson.annotations.SerializedName

data class JokesResult(
    @SerializedName("jokes")
    var jokes: Jokes? = null
)