package me.kwsk.odptlibrary.core.api.bus

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.*
import org.threeten.bp.LocalTime
import org.threeten.bp.ZonedDateTime

/**
 * バス時刻表時刻表
 */
class OdptBusTimetableResponse {
    /**
     * 固有識別子（ucode）
     */
    @SerializedName("@id")
    var odptId: String = ""
    /**
     * データ生成時刻
     */
    @SerializedName("dc:date")
    var date: ZonedDateTime? = null
    /**
     * バス路線名称（系統名等）
     */
    @SerializedName("dc:title")
    var title: String? = null
    /**
     * データ保証期限
     */
    @SerializedName("dct:valid")
    var valid: ZonedDateTime? = null
    /**
     * バス時刻表時分情報リスト
     */
    @SerializedName("odpt:busTimetableObject")
    var busTimetableObjectList: List<OdptBusTimetableObject> = ArrayList()
    /**
     * バス路線ID（API仕様上はrequired）
     * @see OdptBusRoutePatternResponse.sameAs
     */
    @SerializedName("odpt:busroutePattern")
    var busRoutePattern: OdptBusRoutePattern? = null
    /**
     * 運行を行う曜日・日付情報
     */
    @SerializedName("odpt:calendar")
    var calendar: OdptCalendar? = null
    /**
     * バス路線名称のよみがな
     */
    @SerializedName("odpt:kana")
    var kana: String? = null
    /**
     * 運行会社を表す事業者ID（API仕様上はrequired）
     */
    @SerializedName("odpt:operator")
    var operator: OdptOperator? = null
    /**
     * バス時刻表の固有識別子（API仕様上はrequired）
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptBusTimetable? = null
}

/**
 * バス時刻表時分情報
 */
class OdptBusTimetableObject {
    /**
     * バス到着時間
     */
    @SerializedName("odpt:arrivalTime")
    var arrivalTime: LocalTime? = null
    /**
     * バス停のID（API仕様上はrequired）
     * @see OdptBusStopPoleResponse.sameAs
     */
    @SerializedName("odpt:busstopPole")
    var busStopPole: OdptBusStopPole? = null
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
     * 出発時間
     */
    @SerializedName("odpt:departureTime")
    var departureTime: LocalTime? = null
    /**
     * 行き先(方向幕)情報
     */
    @SerializedName("odpt:destinationSign")
    var destinationSign: String? = null
    /**
     * 標柱通過順
     */
    @SerializedName("odpt:index")
    var index: Int = 0
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