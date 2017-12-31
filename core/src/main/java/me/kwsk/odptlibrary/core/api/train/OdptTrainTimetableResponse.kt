package me.kwsk.odptlibrary.core.api.train

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * 列車時刻表
 */
class OdptTrainTimetableResponse {
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
     * 乗車券の他に別料金が必要か？（API仕様上は不要の場合は省略）
     */
    @SerializedName("odpt:needExtraFee")
    var needExtraFee: Boolean = false
    /**
     * 運行会社の事業者IDを格納する（API仕様上はrequired）
     */
    @SerializedName("odpt:operator")
    var operator: OdptOperator? = null
    /**
     * 路線ID（API仕様上はrequired）
     * @see OdptRailwayResponse.sameAs
     */
    @SerializedName("odpt:railway")
    var railway: OdptRailway? = null
    /**
     * 列車ID
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
     * 列車番号、運行管理に用いられる運用番号
     */
    @SerializedName("odpt:trainNumber")
    var trainNumber: String = ""
    /**
     * 車両の所属会社（判明する場合のみ格納）
     */
    @SerializedName("odpt:trainOwner")
    var trainOwner: OdptTrainOwner? = null
    /**
     * 休日（出発時間と出発駅の組か、到着時間と到着駅の組のリストを格納）
     */
    @SerializedName("odpt:trainTimetableObject")
    var trainTimetableObjectList: List<OdptTrainTimetableObject> = ArrayList()
    /**
     * 列車種別のIDを格納する。普通(odpt.TrainType:Local)、急行(odpt.TrainType:Express)、快速(odpt.TrainType:Rapid)、特急(odpt.TrainType:LimitedExpress)など
     */
    @SerializedName("odpt:trainType")
    var trainType: OdptTrainType? = null
    /**
     * 固有識別子。命名ルールは「odpt.TrainTimetable:会社名.路線名.列車番号」（API仕様上はrequired）
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptTrainTimetable? = null
}

/**
 * 列車時刻表データ
 *
 * 始発駅: odpt:departureStationとodpt:departureTime
 * 終着駅: odpt:arrivalStationとodpt:arrivalTime
 * 途中停車駅: odpt:departureStation, odpt:arrivalStationが同一で、odpt:arrivalTime、odpt:departureTimeにそれぞれの値が入る（odpt:departureTimeのみもありえる）
 */
class OdptTrainTimetableObject {
    /**
     * 到着駅の駅ID
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:arrivalStation")
    var arrivalStation: OdptStation? = null
    /**
     * 到着時間
     */
    @SerializedName("odpt:arrivalTime")
    var arrivalTime: LocalTime? = null
    /**
     * 出発駅の駅ID
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:departureStation")
    var departureStation: OdptStation? = null
    /**
     * 出発時間
     */
    @SerializedName("odpt:departureTime")
    var departureTime: LocalTime? = null
    /**
     * 列車が到着 又は出発するプラットフォームの名称
     */
    @SerializedName("odpt:platformName")
    var platformName: String? = null
    /**
     * 列車が到着するプラットフォームの番号
     */
    @SerializedName("odpt:platformNumber")
    var platformNumber: Int? = null
}
