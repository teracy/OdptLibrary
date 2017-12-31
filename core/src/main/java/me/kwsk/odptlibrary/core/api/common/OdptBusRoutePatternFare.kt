package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * バス運賃情報ID。命名ルールは「odpt.BusroutePatternFare:乗車バス路線.乗車バス停.降車バス路線.降車バス停」である
 * ※バス路線はバス路線IDの"odpt.BusroutePattern:"より後の部分
 * @see OdptBusRoutePattern
 */
data class OdptBusRoutePatternFare(var id: String)

class OdptBusRoutePatternFareDeserializer : JsonDeserializer<OdptBusRoutePatternFare> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptBusRoutePatternFare? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.BusroutePatternFare:.*").matches(jsonPrimitive.asString)) {
                return OdptBusRoutePatternFare(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}