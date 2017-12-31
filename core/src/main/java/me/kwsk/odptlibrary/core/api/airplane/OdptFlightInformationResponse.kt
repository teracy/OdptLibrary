package me.kwsk.odptlibrary.core.api.airplane

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * 空港発着情報
 */
class OdptFlightInformationResponse {
    // FIXME: requiredな項目だが、羽田空港のデータには存在しない
    /**
     * 固有識別子（uuid）
     */
    @SerializedName("@id")
    var odptId: String = ""
    // FIXME: @typeは他のAPIと照らし合わせると仕様書が間違っているはず。少なくとも羽田空港のデータではodpt:FlightInformationDepartureまたはodpt:FlightInformationArrivalしかこない
//    /**
//     * クラス指定。国内線出発/到着、国際線出発/到着
//     */
//    @SerializedName("@type")
//    var flightInformationType: String = ""
    /**
     * データ生成時刻
     */
    @SerializedName("dc:date")
    var date: ZonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault())
    // FIXME: 仕様書では"odpt.departureAirport"とtypoされている上に、羽田空港のデータがこれで定義されている
    /**
     * 飛行機の出発地の空港ID
     */
    @SerializedName(value = "departureAirport", alternate = arrayOf("odpt.departureAirport", "odpt:departureAirport"))
    var departureAirport: OdptAirport? = null
    /**
     * 実際の時刻：離陸、着陸するまでは存在しない
     */
    @SerializedName("odpt:actualTime")
    var actualTime: LocalTime? = null
    // FIXME: 仕様では「odpt.Airline:ICAO航空会社コード（3レターコード）」で定義されているが、羽田空港のデータが仕様に従っていないため単純なStringで定義している
    /**
     * 航空会社ID
     */
    @SerializedName("odpt:airline")
    var airline: String? = null
    /**
     * 手荷物受取所ID
     */
    @SerializedName("odpt:baggageClaim")
    var baggageClaim: OdptAirportGate? = null
    /**
     * 飛行機の目的地の空港ID
     */
    @SerializedName("odpt:destinationAirport")
    var destinationAirport: OdptAirport? = null
    /**
     * 変更後時刻：離陸、着陸以降はodpt:actualTimeが生成され、odpt:estimatedTimeは取得できなくなる
     */
    @SerializedName("odpt:estimatedTime")
    var estimatedTime: LocalTime? = null
    // FIXME: 成田空港は仕様書通り"odpt:flightNumber"でobject、羽田空港は仕様書から外れて"odpt:flightNumbers"でarrayで定義されているため、両方を満たすようにした
    /**
     * 便名（フライトナンバー）
     */
    @SerializedName(value = "flightNumber", alternate = arrayOf("odpt:flightNumber", "odpt:flightNumbers"))
    var flightNumber: OdptFlightNumber? = null
    /**
     * フライト状況
     */
    @SerializedName("odpt:flightStatus")
    var flightStatus: OdptFlightStatus? = null
    // FIXME: 仕様とサンプル、また成田空港と羽田空港で食い違っている。仕様と羽田空港の実データは「odpt.AirportGate:IATA空港コード（3レターコード）.ターミナル名.ゲート名」で、サンプルと成田空港の実データは「ゲート名」で定義されている
    /**
     * 出発/到着ゲート番号ID
     */
    @SerializedName("odpt:gate")
    var gate: String? = null
    /**
     * 定刻の時刻
     */
    @SerializedName("odpt:scheduledTime")
    var scheduledTime: LocalTime? = null
    /**
     * 空港ターミナルID
     */
    @SerializedName("odpt:terminal")
    var terminal: OdptAirportTerminal? = null
    // FIXME: 仕様には定義されていないが、サンプルと実データには存在するため定義した
    /**
     * 事業者ID
     */
    @SerializedName("odpt:operator")
    var operator: OdptOperator? = null
    /**
     * 固有識別子（API仕様上はrequired）
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptFlightInformation? = null
}