package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * 航空会社ID。命名ルールは「odpt.Airline:ICAO航空会社コード（3レターコード）」：e.g. "odpt.Airline:ANA"
 */
data class OdptAirline(var id: String)

class OdptAirlineDeserializer : JsonDeserializer<OdptAirline> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptAirline? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.Airline:.*").matches(jsonPrimitive.asString)) {
                return OdptAirline(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}