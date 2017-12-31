package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * バス路線ID。命名ルールは「odpt.BusroutePattern:会社名.系統名.パターン.方向」
 * @see OdptBusRoute
 */
data class OdptBusRoutePattern(var id: String)

class OdptBusRoutePatternDeserializer : JsonDeserializer<OdptBusRoutePattern> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptBusRoutePattern? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.BusroutePattern:.*").matches(jsonPrimitive.asString)) {
                return OdptBusRoutePattern(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}