package swing.dev.cats.retrofit

import swing.dev.cats.models.Cat
import retrofit2.http.*

interface RetrofitHelper {
    @GET("v1/images/search")
    suspend fun search(@Query("include_breeds")includeBreeds:Boolean, @Query("limit")limit: Int, @Query("page")page:Int): List<Cat>?
}