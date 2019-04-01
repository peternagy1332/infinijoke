package yoga.coder.infinijoke.model

import com.google.gson.annotations.SerializedName

data class Joke(

    @SerializedName("genres")
    var genres: MutableList<String>? = null,

    @SerializedName("id")
    var id: String? = null,


    @SerializedName("name")
    var name: String? = null,

    @SerializedName("popularity")
    var popularity: Int? = null,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("uri")
    var uri: String? = null
)