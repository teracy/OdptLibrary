package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * フライト状況ID。命名ルールは「odpt.FlightStatus:ステータス」：e.g. "odpt.FlightStatus:Arrived"
 */
data class OdptFlightStatus(var id: String)

class OdptFlightStatusDeserializer : JsonDeserializer<OdptFlightStatus> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptFlightStatus? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.FlightStatus:.*").matches(jsonPrimitive.asString)) {
                return OdptFlightStatus(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}