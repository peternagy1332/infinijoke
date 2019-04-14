package yoga.coder.infinijoke.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Joke (

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
    var rating: Float,

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int? = null
){
    override fun equals(other: Any?): Boolean {
        if(other !is Joke)
            return false

        if(other.id != id)
            return false

        return true
    }
}