package me.kwsk.odptlibrary.core.api.common

import com.google.gson.*
import java.lang.reflect.Type

/**
 * 便名（フライトナンバー）
 */
data class OdptFlightNumber(var values: List<String>)

class OdptFlightNumberDeserializer : JsonDeserializer<OdptFlightNumber> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptFlightNumber? {
        try {
            if (json?.isJsonPrimitive != false) {
                val jsonPrimitive = json?.asJsonPrimitive
                if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString) {
                    // Stringとして評価できるとき
                    return OdptFlightNumber(listOf(jsonPrimitive.asString))
                }
            }
            if (json?.isJsonArray != false) {
                val jsonArray = json?.asJsonArray
                if (jsonArray is JsonArray && jsonArray.isJsonArray) {
                    // arrayとして評価できるとき
                    val mutableList = mutableListOf<String>()
                    for (jsonElement in jsonArray.asJsonArray) {
                        mutableList.add(jsonElement.asString)
                    }
                    return OdptFlightNumber(mutableList)
                }
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}