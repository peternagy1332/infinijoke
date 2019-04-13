package yoga.coder.infinijoke.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Joke (
    @PrimaryKey
    @SerializedName("id")
    var id: Int,

    @ColumnInfo()
    @SerializedName("id")
    var type: String,

    @ColumnInfo()
    @SerializedName("setup")
    var setup: String,

    @ColumnInfo()
    @SerializedName("punchline")
    var punchline: String,

    @ColumnInfo()
    var rating: Float
)