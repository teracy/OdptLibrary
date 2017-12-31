package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * 運賃情報ID。命名ルールは「odpt.RailwayFare:出発駅の会社名.出発駅の路線名.出発駅名.到着駅の会社名.到着駅の路線名.到着駅名」
 */
data class OdptRailwayFare(var id: String)

class OdptRailwayFareDeserializer : JsonDeserializer<OdptRailwayFare> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptRailwayFare? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString && Regex("^odpt\\.RailwayFare:.*").matches(jsonPrimitive.asString)) {
                return OdptRailwayFare(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}