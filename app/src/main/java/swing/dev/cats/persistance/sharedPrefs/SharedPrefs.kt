package swing.dev.cats.persistance.sharedPrefs

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import swing.dev.cats.models.Cat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SharedPrefs(private val application: Application) {
    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)
    private val DATA = "data"

    var cats: List<Cat>
        get() {
            val json: String? = sharedPreferences.getString(DATA, null)
            val type: Type = object : TypeToken<ArrayList<Cat>>() {}.type
            return Gson().fromJson(json, type)
        }
        set(cities) {
            val editor = sharedPreferences.edit()
            val connectionsJSONString = Gson().toJson(cities)
            editor.putString(DATA, connectionsJSONString)
            editor.commit()
        }

    companion object{
        fun getInstance(application: Application): SharedPrefs {
            synchronized(this){
                var instance: SharedPrefs? = null

                if(instance == null){
                    instance = SharedPrefs(application)
                }

                return  instance
            }
        }
    }
}