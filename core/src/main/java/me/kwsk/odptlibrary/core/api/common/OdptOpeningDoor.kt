package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

enum class OdptOpeningDoor(var id: String) {
    FRONT_SIDE("FrontSide"),
    REAR_SIDE("RearSide");

    companion object {
        fun find(id: String): OdptOpeningDoor? {
            val filter = values().filter { it.id == id }
            return if (filter.isEmpty()) null else filter.get(0)
        }
    }
}

class OdptOpeningDoorDeserializer : JsonDeserializer<OdptOpeningDoor> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptOpeningDoor? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString) {
                return OdptOpeningDoor.find(jsonPrimitive.asString)
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}