package me.kwsk.odptlibrary.core.api.train

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import me.kwsk.odptlibrary.core.api.common.OdptRailway
import me.kwsk.odptlibrary.core.api.common.OdptStation
import me.kwsk.odptlibrary.core.api.common.OdptTrainType
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * 路線情報
 */
class OdptRailwayResponse {
    /**
     * 固有識別子(ucode)。支線には別IDを割り当てる
     */
    @SerializedName("@id")
    var odptId: String = ""
    /**
     * 駅情報の生成時刻
     */
    @SerializedName("dc:date")
    var date: ZonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault())
    /**
     * 運行系統名（e.g. 山手線、成田スカイアクセス線）
     */
    @SerializedName("dc:title")
    var title: String = ""
    /**
     * 路線のラインカラーをHEX表記#RRGGBB、DIC表記DICnnn、PANTONE表記PANTONExxxxで表記する
     */
    @SerializedName("odpt:color")
    var lineColor: String? = null
    /**
     * 運行系統名のよみがな（ひらがな表記）
     */
    @SerializedName("odpt:kana")
    var kana: String? = null
    /**
     * 路線コード、路線シンボル表記を格納（e.g. 丸ノ内線=>M）
     */
    @SerializedName("odpt:lineCode")
    var lineCode: String? = null
    /**
     * 運行会社を表す事業者ID（API仕様上はrequired）
     */
    @SerializedName("odpt:operator")
    var operator: OdptOperator? = null
    /**
     * 駅の順序リスト
     */
    @SerializedName("odpt:stationOrder")
    var stationOrderList: List<OdptStationOrder> = ArrayList()
    /**
     * 路線のシンボル表記 路線の場合は駅ナンバリングに使われている路線記号（e.g. 丸ノ内線=>M）
     */
    @SerializedName("odpt:symbol")
    var lineSymbol: String? = null
    /**
     * 固有識別子。命名ルールは「odpt.Railway:会社名.路線名」（API仕様上はrequired）
     */
    @SerializedName("owl:sameAs")
    var sameAs: OdptRailway? = null
    /**
     * GeoJSON形式による地物情報
     */
    @SerializedName("ug:region")
    var region: String? = null
}

/**
 * 駅順（駅並び）情報
 */
class OdptStationOrder {
    /**
     * 駅の番号11オリジン、又は駅ナンバリングに従う
     */
    @SerializedName("odpt:index")
    var index: Int = 0
    /**
     * 駅ID（API仕様上はrequired）
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:station")
    var station: OdptStation? = null
    /**
     * 駅IDに紐付いたリテラル表記（当該odpt:Stationのdc:titleの値）
     * @see OdptStationResponse.title
     */
    @SerializedName("odpt:stationTitle")
    var stationTitle: String? = null
}

/**
 * 駅間の標準所要時間
 */
class OdptTravelTime {
    /**
     * 駅間の路線距離（メートル）
     */
    @SerializedName("odpt:distance")
    var distance: Int? = null
    /**
     * 駅間の起点となる駅の駅ID（API仕様上はrequired）
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:fromStation")
    var fromStation: OdptStation? = null
    /**
     * 駅間の所要時間（分）
     */
    @SerializedName("odpt:time")
    var necessaryTime: Int = 0
    /**
     * 駅間の終点となる駅の駅ID（API仕様上はrequired）
     * @see OdptStationResponse.sameAs
     */
    @SerializedName("odpt:toStation")
    var toStation: OdptStation? = null
    /**
     * 列車種別を表すID。
     */
    @SerializedName("odpt:trainType")
    var trainType: OdptTrainType? = null
}
