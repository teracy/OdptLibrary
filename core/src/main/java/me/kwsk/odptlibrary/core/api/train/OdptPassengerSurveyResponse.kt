package me.kwsk.odptlibrary.core.api.train

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import me.kwsk.odptlibrary.core.api.common.OdptPassengerSurvey
import me.kwsk.odptlibrary.core.api.common.OdptRailway
import me.kwsk.odptlibrary.core.api.common.OdptStation
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * 駅別乗降人員
 */
class OdptPassengerSurveyResponse {
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
    // NOTE: API仕様書には定義されていないが、xxxTitleという項目は送信される可能性があるという注記があり、この項目はJR東日本の場合に存在するため定義した
    /**
     * 駅名（e.g. 西船橋）
     */
    @SerializedName("dc:title")
    var title: String? = null
    /**
     * 運行会社を表す事業者ID（API仕様上はrequired）
     */
    @SerializedName("odpt:operator")
    var operator: OdptOperator? = null
    /**
     * 駅の1日あたりの平均乗降人員数
     */
    @SerializedName("odpt:passengerJourneys")
    var passengerJourneys: Int = 0
    /**
     * 路線IDのリスト
     * @see OdptRailwayResponse.sameAs
     */
    @SerializedName("odpt:railway")
    var railwayIdList: List<OdptRailway> = ArrayList()
    /**
     * 駅IDのリスト
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:station")
    var stationIdList: List<OdptStation> = ArrayList()
    /**
     * 調査年度
     */
    @SerializedName("odpt:surveyYear")
    var surveyYear: Int = 0
    /**
     * 固有識別子。 命名ルールは「odpt.PassengerSurvey:会社名.駅名.調査年」（API仕様上はrequired）
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptPassengerSurvey? = null
}