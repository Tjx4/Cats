package swing.dev.cats.ui.dashboard.paging

import androidx.paging.PagingSource
import swing.dev.cats.constants.PAGE_SIZE
import swing.dev.cats.helpers.toCatsTable
import swing.dev.cats.models.Cat
import swing.dev.cats.persistance.room.tables.cats.CatImageTable
import swing.dev.cats.repository.CatService
import java.lang.NullPointerException

class CatImagesPagingSource(private val catService: CatService) : PagingSource<Int, CatImageTable>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatImageTable> = try {
        val loadPage = params.key ?: 0

        val response = catService.search(
            `limit` = PAGE_SIZE,
            `page` = loadPage
        )

        if (response.isNullOrEmpty()) {
            LoadResult.Error(NullPointerException("Unknown error"))
        }
        else {
            val currentPage = getCurrentPage(response)
            LoadResult.Page(
                data = currentPage,
                prevKey = null,
                nextKey = loadPage + 1
            )
        }
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    private fun getCurrentPage(response: List<Cat>): List<CatImageTable> {
        val ct = ArrayList<CatImageTable>()
        response.forEach { ct.add(it.toCatsTable()) }
        return ct
    }

}