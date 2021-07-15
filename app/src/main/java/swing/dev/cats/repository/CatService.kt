package swing.dev.cats.repository

import swing.dev.cats.models.Cat
import com.entersekt.shopfinder.networking.retrofit.RetrofitHelper

class CatService(private val retrofitHelper: RetrofitHelper) {
    suspend fun search(limit: Int, page:Int): List<Cat>?{
        return try {
            retrofitHelper.search(false, limit, page)
        }
        catch (ex: Exception){
            null
        }
    }

}