package me.kwsk.odptlibrary.core.api.train

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.*
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * 駅情報
 */
class OdptStationResponse {
    /**
     * 固有識別子（ucode）
     */
    @SerializedName("@id")
    var odptId: String = ""
    /**
     * 駅情報の生成時刻
     */
    @SerializedName("dc:date")
    var date: ZonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault())
    /**
     * 駅名
     */
    @SerializedName("dc:title")
    var title: String? = null
    /**
     * 代表点の経度、10進表記
     */
    @SerializedName("geo:long")
    var longitude: Double? = null
    /**
     * 代表点の緯度、10進表記
     */
    @SerializedName("geo:lat")
    var latitude: Double? = null
    /**
     * 乗り換え可能路線の路線IDのリスト
     * @see OdptRailwayResponse.sameAs
     */
    @SerializedName("odpt:connectingRailway")
    var connectingRailwayIdList: List<OdptRailway>? = null
    /**
     * 駅出入口を表すIDのリスト。IDにはug:Poiの@idの値を利用する
     */
    @SerializedName("odpt:exit")
    var exitIdList: List<String>? = null
    /**
     * 駅施設を表すIDのリスト
     */
    @SerializedName("odpt:facility")
    var facilityIdList: List<String>? = null
    /**
     * 運行系統名のよみがな（ひらがな表記）
     */
    @SerializedName("odpt:kana")
    var kana: String? = null
    /**
     * 運行会社を表す事業者ID（API仕様上はrequired）
     */
    @SerializedName("odpt:operator")
    var operator: OdptOperator? = null
    /**
     * 駅乗降人員数を表す駅乗降人員情報IDのリスト
     * @see OdptPassengerSurveyResponse.sameAs
     */
    @SerializedName("odpt:passengerSurvey")
    var passengerSurveyIdList: List<OdptPassengerSurvey>? = null
    /**
     * 路線を表す路線ID
     * @see OdptRailwayResponse.sameAs
     */
    @SerializedName("odpt:railway")
    var railway: OdptRailway? = null
    /**
     * 駅コード
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:stationCode")
    var stationCode: String? = null
    /**
     * 駅時刻表を表す駅時刻表ID
     * @see OdptStationTimetableResponse.sameAs
     */
    @SerializedName("odpt:stationTimetable")
    var stationTimetableIdList: List<OdptStationTimetable>? = null
    /**
     * 固有識別子。命名ルールは「odpt.Station:会社名.路線名.駅名」（API仕様上はrequired）
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptStation? = null
    /**
     * GeoJSON形式による地物情報
     */
    @SerializedName("ug:region")
    var region: String? = null
}
