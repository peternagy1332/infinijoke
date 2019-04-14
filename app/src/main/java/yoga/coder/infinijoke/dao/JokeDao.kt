package yoga.coder.infinijoke.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import yoga.coder.infinijoke.model.Joke

@Dao
interface JokeDao {
    @Query("SELECT * FROM joke")
    fun getAll(): LiveData<List<Joke>>

    @Query("SELECT DISTINCT type FROM joke")
    fun getAllTypes(): LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(joke: Joke)

    @Query("DELETE FROM joke")
    fun deleteAll()

    @Delete
    fun delete(joke: Joke)
}