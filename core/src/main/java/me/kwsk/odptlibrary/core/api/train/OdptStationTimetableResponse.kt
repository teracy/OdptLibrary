package me.kwsk.odptlibrary.core.api.train

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * 駅時刻表
 */
class OdptStationTimetableResponse {
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
     * 運行を行う曜日・日付情報
     */
    @SerializedName("odpt:calendar")
    var calendar: OdptCalendar? = null
    /**
     * 運行会社を表す事業者ID（API仕様上はrequired）
     */
    @SerializedName("odpt:operator")
    var operator: OdptOperator? = null
    /**
     * 方面を表すID
     */
    @SerializedName("odpt:railDirection")
    var railDirection: OdptRailDirection? = null
    /**
     * 方面名称
     */
    @SerializedName("odpt:railDirectionTitle")
    var railDirectionTitle: String? = null
    /**
     * 路線を表す路線ID（API仕様上はrequired）
     * @see OdptRailwayResponse.sameAs
     */
    @SerializedName("odpt:railway")
    var railway: OdptRailway? = null
    /**
     * 運行会社名称
     */
    @SerializedName("odpt:railwayTitle")
    var railwayTitle: String? = null
    /**
     * 駅を表す駅ID
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:station")
    var station: OdptStation? = null
    /**
     * 出発時間、行き先駅名等の組のリストを格納
     */
    @SerializedName("odpt:stationTimetableObject")
    var stationTimetableObjectList: List<OdptStationTimetableObject>? = null
    /**
     * 駅名称
     */
    @SerializedName("odpt:stationTitle")
    var stationTitle: String? = null
    /**
     * 固有識別子。命名ルールは「odpt.StationTimetable:会社名.路線名.駅名.方面名.曜日種別」（API仕様上はrequired）
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptStationTimetable? = null
}

/**
 * 駅時刻表データ
 */
class OdptStationTimetableObject {
    /**
     * 到着時間
     */
    @SerializedName("odpt:arrivalTime")
    var arrivalTime: LocalTime? = null
    /**
     * 車両数（駅に停車する車両数が列車毎に異なる場合に格納する）
     */
    @SerializedName("odpt:carComposition")
    var carComposition: Int? = null
    /**
     * 出発時間
     */
    @SerializedName("odpt:departureTime")
    var departureTime: LocalTime? = null
    /**
     * 行き先駅を表す駅ID（API仕様上はrequired）
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:destinationStation")
    var destinationStation: OdptStation? = null
    /**
     * 最終電車の場合true（API仕様上は最終電車でない場合は省略）
     */
    @SerializedName("odpt:isLast")
    var isLast: Boolean = false
    /**
     * 始発駅の場合true（API仕様上は始発駅でない場合は省略）
     */
    @SerializedName("odpt:isOrigin")
    var isOrigin: Boolean = false
    /**
     * その他プロパティとして定義されていない注釈情報の自然言語による記載（接続、通過待ちなど）
     */
    @SerializedName("odpt:note")
    var note: String? = null
    /**
     * 到着する列車ID
     * @see OdptTrainResponse.sameAs
     */
    @SerializedName("odpt:train")
    var train: OdptTrain? = null
    /**
     * 編成の名称・愛称
     */
    @SerializedName("odpt:trainName")
    var trainName: String? = null
    /**
     * 列車種別。普通(odpt.TrainType:Local)、急行(odpt.TrainType:Express)、快速(odpt.TrainType:Rapid)、特急(odpt.TrainType:LimitedExpress)など
     */
    @SerializedName("odpt:trainType")
    var trainType: OdptTrainType? = null
}