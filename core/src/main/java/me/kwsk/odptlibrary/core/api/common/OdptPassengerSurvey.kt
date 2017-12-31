package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * 駅乗降人員情報ID。命名ルールは「odpt.PassengerSurvey:会社名.駅名.調査年」
 */
data class OdptPassengerSurvey(var id: String)

class OdptPassengerSurveyDeserializer : JsonDeserializer<OdptPassengerSurvey> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptPassengerSurvey? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.PassengerSurvey:.*").matches(jsonPrimitive.asString)) {
                return OdptPassengerSurvey(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}