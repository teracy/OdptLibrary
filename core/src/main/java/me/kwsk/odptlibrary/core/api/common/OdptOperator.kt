package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * 事業者ID。命名ルールは「odpt.Operator:会社名」
 */
data class OdptOperator(var id: String)

class OdptOperatorDeserializer : JsonDeserializer<OdptOperator> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptOperator? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.Operator:.*").matches(jsonPrimitive.asString)) {
                return OdptOperator(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}

