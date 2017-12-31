package me.kwsk.odptlibrary.core.api.bus

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.*
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * バス運行路線情報
 */
class OdptBusRoutePatternResponse {
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
     * バス位置情報を示すWebSiteのURL
     */
    @SerializedName("odpt:busLocationURL")
    var busLocationURL: String? = null
    /**
     * バス系統iD
     */
    @SerializedName("odpt:busroute")
    var busRoute: OdptBusRoute? = null
    /**
     * バス停留所情報リスト
     */
    @SerializedName("odpt:busstopPoleOrder")
    var busStopPoleOrderList: List<OdptBusStopPoleOrder> = ArrayList()
    /**
     * 方向
     */
    @SerializedName("odpt:direction")
    var direction: String? = null
    /**
     * バス路線名称のよみがな
     */
    @SerializedName("odpt:kana")
    var kana: String? = null
    /**
     * 注記
     */
    @SerializedName("odpt:note")
    var note: String? = null
    /**
     * 運行会社を表す事業者ID（API仕様上はrequired）
     */
    @SerializedName("odpt:operator")
    var operator: OdptOperator? = null
    /**
     * 系統パターン
     */
    @SerializedName("odpt:pattern")
    var pattern: String? = null
    /**
     * バス運行路線情報の固有識別子（API仕様上はrequired）
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptBusRoutePattern? = null
    /**
     * GeoJSON形式による地物情報
     */
    @SerializedName("ug:region")
    var region: String? = null
}

/**
 * バス停留所情報
 */
class OdptBusStopPoleOrder {
    /**
     * バス停ID（API仕様上はrequired）
     * @see OdptBusStopPoleResponse.sameAs
     */
    @SerializedName("odpt:busstopPole")
    var busStopPole: OdptBusStopPole? = null
    /**
     * 停留所通過順。通過順の昇順の値となる
     */
    @SerializedName("odpt:index")
    var index: Int = 0
    /**
     * 注記
     */
    @SerializedName("odpt:note")
    var note: String? = null
    /**
     * 降車時に利用可能なドア
     */
    @SerializedName("odpt:openingDoorsToGetOff")
    var openingDoorsToGetOff: OdptOpeningDoor? = null
    /**
     * 乗車時に利用可能なドア
     */
    @SerializedName("odpt:openingDoorsToGetOn")
    var openingDoorsToGetOn: OdptOpeningDoor? = null
}