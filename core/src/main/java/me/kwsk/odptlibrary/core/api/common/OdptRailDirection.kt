package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * 方面ID。命名ルールは「odpt.RailDirection:会社名.方面名」
 * ただし、JR東日本の列車情報は「odpt.RailwayDirection:会社名.方面名」フォーマットになっている
 */
data class OdptRailDirection(var id: String)

class OdptRailDirectionDeserializer : JsonDeserializer<OdptRailDirection> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptRailDirection? {
        val jsonPrimitive = json?.asJsonPrimitive
        if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString) {
            try {
                // API仕様に則っている場合
                if (jsonPrimitive.isString && Regex("^odpt\\.RailDirection:.*").matches(jsonPrimitive.asString)) {
                    return OdptRailDirection(jsonPrimitive.asString)
                }
                // API仕様に則っていない場合（JR東日本の列車情報）
                if (jsonPrimitive.isString && Regex("^odpt\\.RailwayDirection:.*").matches(jsonPrimitive.asString)) {
                    return OdptRailDirection(jsonPrimitive.asString)
                }
            } catch (e: RuntimeException) {
                return null
            }
        }
        return null
    }
}