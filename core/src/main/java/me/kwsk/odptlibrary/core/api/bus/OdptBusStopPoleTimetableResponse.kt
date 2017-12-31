package me.kwsk.odptlibrary.core.api.bus

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * バス停標柱時刻表
 */
class OdptBusStopPoleTimetableResponse {
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
     * バス路線名称（系統名等）
     */
    @SerializedName("dc:title")
    var title: String? = null
    /**
     * データ保証期限（期限が存在する場合のみ格納）
     */
    @SerializedName("dct:valid")
    var valid: ZonedDateTime? = null
    /**
     * バス方面ID（API仕様上はrequired）
     */
    @SerializedName("odpt:busDirection")
    var busDirection: OdptBusDirection? = null
    /**
     * バス系統ID（API仕様上はrequired）
     */
    @SerializedName("odpt:busroute")
    var busRoute: OdptBusRoute? = null
    /**
     * バス停のID（API仕様上はrequired）
     * @see OdptBusStopPoleResponse.sameAs
     */
    @SerializedName("odpt:busstopPole")
    var busStopPole: OdptBusStopPole? = null
    // FIXME:API仕様書のAPI戻り値サンプルとオブジェクト定義でJSON名が食い違っている。オブジェクト定義では"busstopTimetableObject"だが、サンプルと実際に得られた値は"odpt:busstopPoleTimetableObject"
    /**
     * バス停標柱時刻表時分情報情報リスト
     */
    @SerializedName("odpt:busstopPoleTimetableObject")
    var busStopPoleTimetableObjectList: List<OdptBusStopPoleTimetableObject> = ArrayList()
    /**
     * 運行を行う曜日・日付情報（API仕様上はrequired）
     */
    @SerializedName("odpt:calendar")
    var calendar: OdptCalendar? = null
    /**
     * 運行会社を表す事業者ID（API仕様上はrequired）
     */
    @SerializedName("odpt:operator")
    var operator: OdptOperator? = null
    /**
     * バス停標柱時刻表の固有識別子（API仕様上はrequired）
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptBusStopPoleTimetable? = null
}

/**
 * バス停標柱時刻表時分情報
 */
class OdptBusStopPoleTimetableObject {
    /**
     * バス到着時間
     */
    @SerializedName("odpt:arrivalTime")
    var arrivalTime: LocalTime? = null
    /**
     * バス路線ID
     */
    @SerializedName("odpt:busroutePattern")
    var busRoutePattern: OdptBusRoutePattern? = null
    /**
     * 降車可能な場合true
     */
    @SerializedName("odpt:canGetOff")
    var canGetOff: Boolean? = null
    /**
     * 乗車可能な場合true
     */
    @SerializedName("odpt:canGetOn")
    var canGetOn: Boolean? = null
    /**
     * バス出発時間
     */
    @SerializedName("odpt:departureTime")
    var departureTime: LocalTime? = null
    /**
     * 行き先バス停標柱
     */
    @SerializedName("odpt:destinationBusstopPole")
    var destinationBusStopPole: OdptBusStopPole? = null
    /**
     * 行き先(方向幕)情報
     */
    @SerializedName("odpt:destinationSign")
    var destinationSign: String? = null
    /**
     * 深夜バスの場合true
     */
    @SerializedName("odpt:isMidnight")
    var isMidnight: Boolean? = null
    /**
     * ノンステップバスの場合true
     */
    @SerializedName("odpt:isNonStepBus")
    var isNonStepBus: Boolean? = null
    /**
     * 注記
     */
    @SerializedName("odpt:note")
    var note: String? = null
}