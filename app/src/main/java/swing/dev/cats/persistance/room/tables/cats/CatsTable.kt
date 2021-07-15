package swing.dev.cats.persistance.room.tables.cats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catImageTable")
data class CatImageTable (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id:String,
    @ColumnInfo(name ="name")
    var name: String?,
    @ColumnInfo(name ="url")
    var url: String?
)