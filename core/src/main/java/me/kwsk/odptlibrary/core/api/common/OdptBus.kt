package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * バス停標柱情報ID。命名ルールは「odpt.Bus:バス路線.バス番号」
 * ※バス路線はバス路線IDの"odpt.BusroutePattern:"より後の部分
 * @see OdptBusRoutePattern
 */
data class OdptBus(var id: String)

class OdptBusDeserializer : JsonDeserializer<OdptBus> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptBus? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.Bus:.*").matches(jsonPrimitive.asString)) {
                return OdptBus(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}