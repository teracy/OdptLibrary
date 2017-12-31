package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * 車両所属会社ID。命名ルールは「odpt.TrainOwner:会社名」
 */
data class OdptTrainOwner(var id: String)

class OdptTrainOwnerDeserializer : JsonDeserializer<OdptTrainOwner> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptTrainOwner? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.TrainOwner:.*").matches(jsonPrimitive.asString)) {
                return OdptTrainOwner(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}