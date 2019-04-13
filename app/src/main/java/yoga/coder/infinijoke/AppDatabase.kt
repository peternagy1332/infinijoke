package yoga.coder.infinijoke

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import yoga.coder.infinijoke.dao.JokeDao
import yoga.coder.infinijoke.model.Joke

@Database(entities = arrayOf(Joke::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun jokeDao(): JokeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "infinijoke_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}