package yoga.coder.infinijoke.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import yoga.coder.infinijoke.model.Joke

@Dao
interface JokeDao {
    @Query("SELECT * from joke")
    fun getAll(): LiveData<List<Joke>>

    @Insert
    fun insert(joke: Joke)

    @Query("DELETE FROM joke")
    fun deleteAll()
}