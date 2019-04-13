package yoga.coder.infinijoke.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import yoga.coder.infinijoke.model.Joke

@Dao
interface JokeDao {
    @Query("SELECT * from joke")
    fun getAll(): LiveData<List<Joke>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(joke: Joke)

    @Query("DELETE FROM joke")
    fun deleteAll()

    @Delete
    fun delete(joke: Joke)
}