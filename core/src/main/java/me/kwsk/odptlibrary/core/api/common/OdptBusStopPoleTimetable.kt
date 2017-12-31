package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * バス停標柱時刻表ID。命名ルールは「odpt.OdptBusstopPoleTimetable:会社名.路線名.バス停標柱.方面名.Calender」
 * ※バス停標柱はバス停標柱IDの"odpt.BusstopPole:"より後の部分
 */
data class OdptBusStopPoleTimetable(var id: String)

class OdptBusStopPoleTimetableDeserializer : JsonDeserializer<OdptBusStopPoleTimetable> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptBusStopPoleTimetable? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.BusstopPoleTimetable:.*").matches(jsonPrimitive.asString)) {
                return OdptBusStopPoleTimetable(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}