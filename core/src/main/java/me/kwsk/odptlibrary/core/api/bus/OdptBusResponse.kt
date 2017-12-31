package me.kwsk.odptlibrary.core.api.bus

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.*
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * バス情報
 */
class OdptBusResponse {
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
    var valid: ZonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault())
    /**
     * バスの緯度
     */
    @SerializedName("geo:lat")
    var latitude: Double? = null
    /**
     * バスの経度
     */
    @SerializedName("geo:long")
    var longitude: Double? = null
    /**
     * バスの進行方向方位角。単位は度（°）
     */
    @SerializedName("odpt:azimuth")
    var azimuth: Double? = null
    /**
     * バス車両番号
     */
    @SerializedName("odpt:busNumber")
    var busNumber: String = ""
    /**
     * バス路線ID（API仕様上はrequired）
     * @see OdptBusRoutePatternResponse.sameAs
     */
    @SerializedName("odpt:busroutePattern")
    var busRoutePattern: OdptBusRoutePattern? = null
    /**
     * バスの扉の開閉状態
     */
    @SerializedName("odpt:doorStatus")
    var doorStatus: OdptDoorStatus? = null
    /**
     * 更新頻度（秒）、指定された秒数以降にリクエストを行うことで、最新値が取得される
     */
    @SerializedName("odpt:frequency")
    var frequency: Int = 0
    /**
     * 直前のバス停のID（API仕様上はrequired）
     * @see OdptBusStopPoleResponse.sameAs
     */
    @SerializedName("odpt:fromBusstopPole")
    var fromBusStopPole: OdptBusStopPole? = null
    /**
     * 直前バス停を発車した時刻
     */
    @SerializedName("odpt:fromBusstopPoleTime")
    var fromBusStopPoleTime: ZonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault())
    /**
     * 運行会社を表す事業者ID（API仕様上はrequired）
     */
    @SerializedName("odpt:operator")
    var operator: OdptOperator? = null
    /**
     * Fromを0、toを1とした際の現在位置（割合）
     */
    @SerializedName("odpt:progress")
    var progress: Double? = null
    /**
     * バスの速度(km/h)
     */
    @SerializedName("odpt:speed")
    var speed: Double? = null
    /**
     * 始発バス停のID
     * @see OdptBusStopPoleResponse.sameAs
     */
    @SerializedName("odpt:startingBusstopPole")
    var startingBusStopPole: OdptBusStopPole? = null
    /**
     * 終着バス停のID
     * @see OdptBusStopPoleResponse.sameAs
     */
    @SerializedName("odpt:terminalBusstopPole")
    var terminalBusStopPole: OdptBusStopPole? = null
    /**
     * 次のバス停のID。バス停に停車中の場合には、odpt:toBusstopは存在しない
     * @see OdptBusStopPoleResponse.sameAs
     */
    @SerializedName("odpt:toBusstopPole")
    var toBusStopPole: OdptBusStopPole? = null
    /**
     * バスの固有識別子
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptBus? = null
}