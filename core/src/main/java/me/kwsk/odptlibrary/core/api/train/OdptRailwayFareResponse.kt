package me.kwsk.odptlibrary.core.api.train

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import me.kwsk.odptlibrary.core.api.common.OdptRailwayFare
import me.kwsk.odptlibrary.core.api.common.OdptStation
import me.kwsk.odptlibrary.core.api.common.OdptTicketType
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * 運賃
 */
class OdptRailwayFareResponse {
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
     * ICカード利用時の子供運賃
     */
    @SerializedName("odpt:childIcCardFare")
    var childIcCardFare: Int? = 0
    /**
     * 切符利用時の子供運賃
     */
    @SerializedName("odpt:childTicketFare")
    var childTicketFare: Int? = 0
    /**
     * 駅間の始点駅を表すID。IDにはodpt:Stationのowl:sameAsの値を利用する（API仕様上はrequired）
     */
    @SerializedName("odpt:fromStation")
    var fromStation: OdptStation? = null
    /**
     * ICカード利用時の運賃
     */
    @SerializedName("odpt:icCardFare")
    var icCardFare: Int? = 0
    /**
     * 運行会社を表す事業者ID（API仕様上はrequired）
     */
    @SerializedName("odpt:operator")
    var operator: OdptOperator? = null
    /**
     * 支払い方法リスト
     */
    @SerializedName("odpt:paymentMethod")
    var paymentMethodList: List<String>? = null
    /**
     * 切符利用時の運賃
     */
    @SerializedName("odpt:ticketFare")
    var ticketFare: Int? = 0
    /**
     * チケット種別（特急、ライナーなど列車種別によって料金が異なる場合に記載）
     */
    @SerializedName("odpt:ticketType")
    var ticketType: OdptTicketType? = null
    /**
     * 駅間の終点駅を表す駅ID（API仕様上はrequired）
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:toStation")
    var toStation: OdptStation? = null
    // NOTE: 命名ルールがAPI仕様から逸脱して「odpt.RailwayFare:出発駅の会社名.出発駅の路線名.出発駅名.到着駅の路線名.到着駅名」となっている事業者が存在するので注意（存在するというより東京メトロ以外全社）
    /**
     * 固有識別子。命名ルールは「odpt.RailwayFare:出発駅の会社名.出発駅の路線名.出発駅名.到着駅の会社名.到着駅の路線名.到着駅名」（API仕様上はrequired）
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptRailwayFare? = null
}