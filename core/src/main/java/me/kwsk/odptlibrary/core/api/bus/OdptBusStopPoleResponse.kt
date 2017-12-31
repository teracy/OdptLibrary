package me.kwsk.odptlibrary.core.api.bus

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.OdptBusRoutePattern
import me.kwsk.odptlibrary.core.api.common.OdptBusStopPole
import me.kwsk.odptlibrary.core.api.common.OdptBusStopPoleTimetable
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * バス停標柱情報
 */
class OdptBusStopPoleResponse {
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
     * バス停名
     */
    @SerializedName("dc:title")
    var title: String? = null
    /**
     * データ保証期限
     */
    @SerializedName("dct:valid")
    var valid: ZonedDateTime? = null
    /**
     * 標柱の緯度(WGS84)
     */
    @SerializedName("geo:lat")
    var latitude: Double? = null
    /**
     * 標柱の経度(WGS84)
     */
    @SerializedName("geo:long")
    var longitude: Double? = null
    /**
     * バス路線IDのリスト
     * @see OdptBusRoutePatternResponse.sameAs
     */
    @SerializedName("odpt:busroutePattern")
    var busRoutePatternList: List<OdptBusRoutePattern>? = null
    /**
     * 標柱番号。同一停留所の別標柱を区別するものであり、のりば番号とは一致する保証はない
     */
    @SerializedName("odpt:busstopPoleNumber")
    var busStopPoleNumber: String? = null
    /**
     * バス停時刻表IDのリスト
     * @see OdptBusStopPoleTimetableResponse.sameAs
     */
    @SerializedName("odpt:busstopPoleTimetable")
    var busStopPoleTimetableList: List<OdptBusStopPoleTimetable>? = null
    /**
     * 事業者IDのリスト
     */
    @SerializedName("odpt:operator")
    var operatorList: List<OdptOperator> = ArrayList()
    /**
     * バス停標柱情報の固有識別子
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptBusStopPole? = null
    /**
     * GeoJSON形式による地物情報
     */
    @SerializedName("ug:region")
    var region: String? = null
}