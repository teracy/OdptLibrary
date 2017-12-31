package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * バス時刻表ID。命名ルールは「odpt.BusTimetable:バス路線.Calender」
 * ※バス路線はバス路線IDの"odpt.BusroutePattern:"より後の部分
 * @see OdptBusRoutePattern
 */
data class OdptBusTimetable(var id: String)

class OdptBusTimetableDeserializer : JsonDeserializer<OdptBusTimetable> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptBusTimetable? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.BusTimetable:.*").matches(jsonPrimitive.asString)) {
                return OdptBusTimetable(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}