package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * 駅時刻表ID。命名ルールは「odpt.StationTimetable:会社名.路線名.駅名.方面名.曜日種別」
 */
data class OdptStationTimetable(var id: String)

class OdptStationTimetableDeserializer : JsonDeserializer<OdptStationTimetable> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptStationTimetable? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.StationTimetable:.*").matches(jsonPrimitive.asString)) {
                return OdptStationTimetable(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}