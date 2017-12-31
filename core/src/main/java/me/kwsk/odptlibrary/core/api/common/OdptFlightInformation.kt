package me.kwsk.odptlibrary.core.api.common

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import java.lang.reflect.Type

// FIXME: 仕様の命名ルール（そもそもtypoがあるので怪しいが）に則るとサンプルデータがinvalidになるため、本当に正しいルールがよくわからない
/**
 * 空港発着情報ID。仕様書によれば命名ルールは以下の通り
 * 到着情報の場合は「odpt.FlightInformationArrival:到着空港のIATA空港コード（3レターコード）.便名（フライトナンバー）」
 * 出発情報の場合は「odpt.FlightInformationDeparture:出発空港のIATA空港コード（3レターコード）.便名（フライトナンバー）」
 * e.g. "odpt.FlightInformationArrival:HND.MM0858", "odpt.FlightInformationDeparture:HND.NH0203"
 *
 * しかし、サンプルデータがこれに則っていないことと成田空港の実データから、本当の命名ルールは以下の通りと推測（ただしこのルールでもサンプルデータはinvalid）
 * 到着情報の場合は「odpt.FlightInformationArrival:到着空港のIATA空港コード（3レターコード）.運行するICAO航空会社コード（3レターコード）.便名（フライトナンバー）」
 * 出発情報の場合は「odpt.FlightInformationDeparture:出発空港のIATA空港コード（3レターコード）.運行するICAO航空会社コード（3レターコード）.便名（フライトナンバー）」
 * e.g. "odpt.FlightInformationArrival:NRT.JJP.GK0501", "odpt.FlightInformationArrival:NRT.AMX.AM0058"
 *
 * あるいは、成田空港と羽田空港でルールが異なると考えた方がいいかもしれない
 */
data class OdptFlightInformation(var id: String)

class OdptFlightInformationDeserializer : JsonDeserializer<OdptFlightInformation> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OdptFlightInformation? {
        val jsonPrimitive = json?.asJsonPrimitive
        try {
            if (jsonPrimitive is JsonPrimitive && jsonPrimitive.isString) {
                if (Regex("^odpt\\.FlightInformationArrival:.*").matches(jsonPrimitive.asString)
                        || Regex("^odpt\\.FlightInformationDeparture:.*").matches(jsonPrimitive.asString)) {
                    return OdptFlightInformation(jsonPrimitive.asString)
                }
            }
        } catch (e: RuntimeException) {
            return null
        }
        return null
    }
}