package swing.dev.cats.helpers

import swing.dev.cats.models.Cat
import swing.dev.cats.persistance.room.tables.cats.CatImageTable

fun Cat.toCatsTable(): CatImageTable {
    return CatImageTable(this.id ?: "", this.name, this.url)
}
