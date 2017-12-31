package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * 空港時刻表ID。命名ルールは「odpt.FlightSchedule:会社名.曜日種別」：e.g. "odpt.FlightSchedule:ANA.Thursday"
 */
data class OdptFlightSchedule(var id: String)

class OdptFlightScheduleDeserializer : JsonDeserializer<OdptFlightSchedule> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptFlightSchedule? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.FlightSchedule:.*").matches(jsonPrimitive.asString)) {
                return OdptFlightSchedule(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}