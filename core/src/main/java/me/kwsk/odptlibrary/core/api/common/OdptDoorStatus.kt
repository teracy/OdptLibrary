package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

/**
 * バス扉開閉状態
 */
enum class OdptDoorStatus(var id: String) {
    /**
     * 開いている
     */
    OPEN("open"),
    /**
     * 閉じている
     */
    CLOSE("close"),
    /**
     * 半自動扱い
     */
    SELF("self");

    companion object {
        fun find(id: String): OdptDoorStatus? {
            val filter = values().filter { it.id == id }
            return if (filter.isEmpty()) null else filter.get(0)
        }
    }
}

class OdptDoorStatusDeserializer : JsonDeserializer<OdptDoorStatus> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptDoorStatus? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString) {
                return OdptDoorStatus.find(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}