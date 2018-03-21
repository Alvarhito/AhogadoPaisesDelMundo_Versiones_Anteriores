package com.example.ceisutb01.ahogadopaisesdelmundo
import com.github.kittinunf.fuel.android.core.Json
import org.json.JSONException
import org.json.JSONObject
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import android.util.Log
import org.json.JSONArray


class Pais {

    private var name: String? = null
    private var front_default_url: String? = null
    private var back_default_url: String? = null
    private var info_Sprites: JSONObject? = null

    private var image: JSONObject = JSONObject()

    fun getName(info: Json): String {
        try {
            image =info.array().getJSONObject(1)
            return image.getJSONObject("translations").getString("es")
            //val ints2 = gson.fromJson("translations", Array<String>::class.java)
            //name=ints2[0].toString()
        } catch (e: JSONException) {
            return "No hay nada"
            e.printStackTrace()
        }
    }

    /*private fun getSprites(info: JSONObject): JSONObject? {
        info_Sprites = null
        try {
            info_Sprites = info.getJSONObject("sprites")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return info_Sprites
    }


    fun getImage_Front(info: JSONObject): String? {

        var pkm= Pais()
        image = pkm.getSprites(info)

        try {

            front_default_url = image!!.getString("front_default")


        } catch (e: JSONException) {

            e.printStackTrace()

        }

        return front_default_url
    }
    fun getImage_Back(info: JSONObject): String? {

        var pkm= Pais()
        image = pkm.getSprites(info)

        try {

            back_default_url = image!!.getString("back_default")


        } catch (e: JSONException) {

            e.printStackTrace()

        }

        return back_default_url
    }

*/
}