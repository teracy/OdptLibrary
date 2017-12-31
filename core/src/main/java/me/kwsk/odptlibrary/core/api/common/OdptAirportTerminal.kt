package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * 空港ID。命名ルールは「odpt.AirportTerminal:IATA空港コード（3レターコード）.ターミナル名」：e.g. "odpt.AirportTerminal:NRT.Terminal2"
 */
data class OdptAirportTerminal(var id: String)

class OdptAirportTerminalDeserializer : JsonDeserializer<OdptAirportTerminal> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptAirportTerminal? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.AirportTerminal:.*").matches(jsonPrimitive.asString)) {
                return OdptAirportTerminal(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}