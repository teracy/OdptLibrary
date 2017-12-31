package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * バス方面ID。命名ルールは「odpt.BusDirection:会社名.方面名」
 */
data class OdptBusDirection(var id: String)

class OdptBusDirectionDeserializer : JsonDeserializer<OdptBusDirection> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptBusDirection? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.BusDirection:.*").matches(jsonPrimitive.asString)) {
                return OdptBusDirection(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}