package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * 曜日・日付情報ID。命名ルールは「odpt.Calendar:曜日・日付」
 */
data class OdptCalendar(var id: String)

class OdptCalendarDeserializer : JsonDeserializer<OdptCalendar> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptCalendar? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.Calendar:.*").matches(jsonPrimitive.asString)) {
                return OdptCalendar(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}