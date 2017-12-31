package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * 空港ID。命名ルールは「odpt.AirportGate:IATA空港コード（3レターコード）.ターミナル名.ゲート名」：e.g. "odpt.AirportGate:HND"
 */
data class OdptAirportGate(var id: String)

class OdptAirportGateDeserializer : JsonDeserializer<OdptAirportGate> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptAirportGate? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.AirportGate:.*").matches(jsonPrimitive.asString)) {
                return OdptAirportGate(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}