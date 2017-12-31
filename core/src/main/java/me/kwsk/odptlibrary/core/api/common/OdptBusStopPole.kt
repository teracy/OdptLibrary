package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

// TODO バス停名と標柱番号の間に挟まる数値のみ謎
/**
 * バス停標柱ID。命名ルールは「odpt.BusstopPole:会社名.バス停名.バス停ID?.標柱番号」
 */
data class OdptBusStopPole(var id: String)

class OdptBusStopPoleDeserializer : JsonDeserializer<OdptBusStopPole> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptBusStopPole? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.BusstopPole:.*").matches(jsonPrimitive.asString)) {
                return OdptBusStopPole(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}