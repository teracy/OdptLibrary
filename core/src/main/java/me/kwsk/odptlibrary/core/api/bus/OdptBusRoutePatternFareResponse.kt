package me.kwsk.odptlibrary.core.api.bus

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.OdptBusRoutePattern
import me.kwsk.odptlibrary.core.api.common.OdptBusRoutePatternFare
import me.kwsk.odptlibrary.core.api.common.OdptBusStopPole
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

// FIXME: データ量が膨大で、事業者IDだけで絞り込むと先頭1000件がすぐ取れるが、他の条件を加えるとサーバーが容易にエラーを吐くため実用に耐えないAPI
/**
 * バス運賃情報
 */
class OdptBusRoutePatternFareResponse {
    /**
     * 固有識別子
     */
    @SerializedName("@id")
    var odptId: String = ""
    /**
     * データ生成時刻
     */
    @SerializedName("dc:date")
    var date: ZonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault())
    /**
     * データ保証期限
     */
    @SerializedName("dct:valid")
    var valid: ZonedDateTime? = null
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
     * 乗車系統バス路線ID（API仕様上はrequired）
     * @see OdptBusRoutePatternResponse.sameAs
     */
    @SerializedName("odpt:fromBusroutePattern")
    var fromBusroutePattern: OdptBusRoutePattern? = null
    /**
     * 乗車バス停を表すID（API仕様上はrequired）
     * @see OdptBusStopPoleResponse.sameAs
     */
    @SerializedName("odpt:fromBusstopPole")
    var fromBusStopPole: OdptBusStopPole? = null
    /**
     * 乗車停留所の系統パターン内の停留所（標柱）通過順。odpt:fromBusroutePattern の示す odpt:BusroutePattern の、 odpt:busstopPoleOrder の odpt:index と同じ値
     * @see OdptBusStopPoleOrder.index
     */
    @SerializedName("odpt:fromBusstopPoleOrder")
    var fromBusStopPoleOrder: Int = 0
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
     * 切符利用時の運賃
     */
    @SerializedName("odpt:ticketFare")
    var ticketFare: Int? = 0
    /**
     * 降車系統バス路線ID（API仕様上はrequired）
     * @see OdptBusRoutePatternResponse.sameAs
     */
    @SerializedName("odpt:toBusroutePattern")
    var toBusroutePattern: OdptBusRoutePattern? = null
    /**
     * 降車バス停を表すID（API仕様上はrequired）
     * @see OdptBusStopPoleResponse.sameAs
     */
    @SerializedName("odpt:toBusstopPole")
    var toBusStopPole: OdptBusStopPole? = null
    /**
     * 降車停留所の系統パターン内の停留所（標柱）通過順。odpt:toBusroutePattern の示す odpt:BusroutePattern の、 odpt:busstopPoleOrder の odpt:index と同じ値。
     * @see OdptBusStopPoleOrder.index
     */
    @SerializedName("odpt:toBusstopPoleOrder")
    var toBusStopPoleOrder: Int = 0
    // TODO: OdptBusRoutePatternFare作成
    /**
     * バス運賃情報の固有識別子（API仕様上はrequired）
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptBusRoutePatternFare? = null
}