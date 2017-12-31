package me.kwsk.odptlibrary.core.api

import com.google.gson.*
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.lang.reflect.Type

/**
 * ISO8601日付時刻形式→ZonedDateTimeのデシリアライザ
 */
class IsoDateTimeDeserializer : JsonDeserializer<ZonedDateTime> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ZonedDateTime {
        val jsonPrimitive = json?.asJsonPrimitive
        if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString) {
            try {
                return ZonedDateTime.parse(jsonPrimitive.asString, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            } catch (e: RuntimeException) {
                try {
                    // NOTE: 小田急バスのdct:validがISO_DATEで送られてくるため、二段構えで処理する
                    // 少々荒っぽいが、ISO_DATEのstartOfDayに+1日-1秒して当日の23:59:59までを期限にしている
                    return LocalDate.parse(jsonPrimitive.asString, DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneOffset.ofHours(9)).plusDays(1).minusSeconds(1)
                } catch (e: RuntimeException) {
                    throw JsonParseException("Unable to parse ZonedDateTime")
                }
            }
        } else {
            throw JsonParseException("Unable to parse ZonedDateTime")
        }
    }
}

/**
 * ISO8601時刻形式→LocalTimeのデシリアライザ
 */
class IsoTimeDeserializer : JsonDeserializer<LocalTime> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalTime {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString) {
                return LocalTime.parse(jsonPrimitive.asString, DateTimeFormatter.ISO_TIME)
            }
        } catch (e: RuntimeException) {
            throw JsonParseException("Unable to parse LocalTime", e)
        }
        throw JsonParseException("Unable to parse LocalTime")
    }
}