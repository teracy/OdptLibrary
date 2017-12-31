package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * 空港ID。命名ルールは「odpt.Airport:IATA空港コード（3レターコード）」：e.g. "odpt.Airport:HND"
 */
data class OdptAirport(var id: String)

class OdptAirportDeserializer : JsonDeserializer<OdptAirport> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptAirport? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.Airport:.*").matches(jsonPrimitive.asString)) {
                return OdptAirport(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}