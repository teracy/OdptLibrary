package me.kwsk.odptlibrary.core.api.train

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.*
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * 列車情報
 *
 * odpt:fromStaion, odpt:toStationへの情報の入り方によって列車の現在位置状態を示す
 * odpt:fromStaion == A && odpt:toStation == null -> A駅に停車中
 * odpt:fromStaion == A && odpt:toStation == B -> A駅-B駅間を走行中
 */
class OdptTrainResponse {
    /**
     * 固有識別子（ucode又はuuid）
     */
    @SerializedName("@id")
    var odptId: String = ""
    /**
     * データ生成時刻
     */
    @SerializedName("dc:date")
    var date: ZonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault())
    /**
     * 遅延時間（秒）
     */
    @SerializedName("odpt:delay")
    var delay: Int? = null
    /**
     * 列車が出発した駅の駅ID
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:fromStation")
    var fromStation: OdptStation? = null
    // NOTE: API仕様書には定義されていないが、xxxTitleという項目は送信される可能性があるという注記があり、この項目はJR東日本の場合に存在するため定義した
    /**
     * 列車が出発した駅の名称
     */
    @SerializedName("odpt:fromStationTitle")
    var fromStationTitle: String? = null
    /**
     * 進行方面を表すID
     */
    @SerializedName("odpt:railDirection")
    var railDirection: OdptRailDirection? = null
    /**
     * 鉄道路線の路線ID（API仕様上はrequired）
     * @see OdptRailwayResponse.sameAs
     */
    @SerializedName("odpt:railway")
    var railway: OdptRailway? = null
    /**
     * 列車の始発駅の駅ID
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:startingStation")
    var startingStation: OdptStation? = null
    /**
     * 列車の終着駅の駅ID
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:terminalStation")
    var terminalStation: OdptStation? = null
    /**
     * 列車が向かっている駅の駅ID。通過する場合、通過する駅のIDが入る
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:toStation")
    var toStation: OdptStation? = null
    // NOTE: API仕様書には定義されていないが、xxxTitleという項目は送信される可能性があるという注記があり、この項目はJR東日本の場合に存在するため定義した
    /**
     * 列車が向かっている駅の名称
     */
    @SerializedName("odpt:toStationTitle")
    var toStationTitle: String? = null
    /**
     * 列車番号、運行管理に用いられる運用番号
     */
    @SerializedName("odpt:trainNumber")
    var trainNumber: String = ""
    /**
     * 車両の所属会社の事業者ID
     */
    @SerializedName("odpt:trainOwner")
    var trainOwner: OdptTrainOwner? = null
    /**
     * 列車種別ID、語彙一覧を参照
     */
    @SerializedName("odpt:trainType")
    var trainType: OdptTrainType? = null
    // NOTE: API仕様書には定義されていないが、xxxTitleという項目は送信される可能性があるという注記があり、この項目はJR東日本の場合に存在するため定義した
    /**
     * 列車種別名称
     */
    @SerializedName("odpt:trainTypeTitle")
    var trainTypeTitle: String? = null
    /**
     * 固有識別子。命名ルールは「odpt.Train:会社名.路線名.列車番号」（API仕様上はrequired）
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptTrain? = null
}