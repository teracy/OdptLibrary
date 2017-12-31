package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * 列車時刻表ID。命名ルールは「odpt.TrainTimetable:会社名.路線名.列車番号」
 */
data class OdptTrainTimetable(var id: String)

class OdptTrainTimetableDeserializer : JsonDeserializer<OdptTrainTimetable> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptTrainTimetable? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.TrainTimetable:.*").matches(jsonPrimitive.asString)) {
                return OdptTrainTimetable(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}