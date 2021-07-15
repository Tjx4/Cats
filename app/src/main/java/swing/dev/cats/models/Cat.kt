package swing.dev.cats.models

import com.google.gson.annotations.SerializedName

data class Cat(
    @SerializedName("id") var id: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("url") var url: String?
)
