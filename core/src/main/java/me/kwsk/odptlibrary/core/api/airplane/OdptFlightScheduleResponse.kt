package me.kwsk.odptlibrary.core.api.airplane

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * 空港時刻表
 */
class OdptFlightScheduleResponse {
    // FIXME: requiredな項目だが、全日空のデータには存在しない
    /**
     * 固有識別子（ucode）
     */
    @SerializedName("@id")
    var odptId: String = ""
    /**
     * データ生成時刻
     */
    @SerializedName("dc:date")
    var date: ZonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault())
    /**
     * 空港時刻表オブジェクトリスト
     */
    @SerializedName("odpt:FlightScheduleObject")
    var flightScheduleObjectList: List<OdptFlightScheduleResponseObject> = ArrayList()
    /**
     * 時刻表に対応するカレンダー情報（API仕様上はrequired）
     */
    @SerializedName("odpt:calendar")
    var calendar: OdptCalendar? = null
    /**
     * データ提供事業者のID（API仕様上はrequired）
     */
    @SerializedName("odpt:operator")
    var operator: OdptOperator? = null
    /**
     * 固有識別子（API仕様上はrequired）
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptFlightSchedule? = null
}

/**
 * 空港時刻表オブジェクト
 */
class OdptFlightScheduleResponseObject {
    /**
     * 運行航空会社のID（API仕様上はrequired）
     */
    @SerializedName("odpt:airline")
    var airline: OdptAirline? = null
    /**
     * 到着予定時間（API仕様上はrequired）
     */
    @SerializedName("odpt:arrivalTime")
    var arrivalTime: LocalTime? = null
    /**
     * 出発地の空港ID
     */
    @SerializedName("odpt:departureAirport")
    var departureAirport: OdptAirport? = null
    /**
     * 出発予定時間（API仕様上はrequired）
     */
    @SerializedName("odpt:departureTime")
    var departureTime: LocalTime? = null
    /**
     * 到着空港のID
     */
    @SerializedName("odpt:destinationAirport")
    var destinationAirport: OdptAirport? = null
    /**
     * 便名（フライトナンバー）
     */
    @SerializedName("odpt:flightNumber")
    var flightNumber: String? = null
    /**
     * データ適用開始日時
     */
    @SerializedName("odpt:isValidFrom")
    var validFrom: ZonedDateTime? = null
}