package swing.dev.cats.persistance.room.tables.cats

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CatImageDAO {
    @Insert
    fun insert(catImageTable: CatImageTable)

    @Update
    fun update(catImageTable: CatImageTable)

    @Delete
    fun delete(catImageTable: CatImageTable)

    @Query("SELECT * FROM catImageTable WHERE id = :key")
    fun get(key: Long): CatImageTable?

    @Query("SELECT * FROM catImageTable ORDER BY id DESC LIMIT 1")
    fun getLastUser(): CatImageTable?

    @Query("SELECT * FROM catImageTable ORDER BY id DESC")
    fun getAllHistoriesLiveData(): LiveData<List<CatImageTable>>

    @Query("SELECT * FROM catImageTable ORDER BY id DESC")
    fun getAllHistories():List<CatImageTable>?

    @Query("DELETE  FROM catImageTable")
    fun clear()
}