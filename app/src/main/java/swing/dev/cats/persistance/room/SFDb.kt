package swing.dev.cats.persistance.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import swing.dev.cats.persistance.room.tables.cats.CatImageDAO
import swing.dev.cats.persistance.room.tables.cats.CatImageTable

@Database(entities = [CatImageTable::class], version = 1, exportSchema = false)
 abstract class CDb : RoomDatabase() {
    abstract val catImageDAO: CatImageDAO

    companion object{
        @Volatile
        private var INSTANCE: CDb? = null

        fun getInstance(context: Context): CDb {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext, CDb::class.java, "c_db")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return  instance
            }
        }
    }
}