package me.kwsk.odptlibrary.core.api.train

import com.google.gson.annotations.SerializedName
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import me.kwsk.odptlibrary.core.api.common.OdptRailway
import me.kwsk.odptlibrary.core.api.common.OdptStation
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * 運行情報
 */
class OdptTrainInformationResponse {
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
     * 有効期限
     */
    @SerializedName("dct:valid")
    var valid: ZonedDateTime? = null
    /**
     * 運行会社を表す事業者ID（API仕様上はrequired）
     */
    @SerializedName("odpt:operator")
    var operator: OdptOperator? = null
    /**
     * 発生路線を表す路線ID（API仕様上はrequired）
     * @see OdptRailwayResponse.sameAs
     */
    @SerializedName("odpt:railway")
    var railway: OdptRailway? = null
    // NOTE: API仕様書には定義されていないが、xxxTitleという項目は送信される可能性があるという注記があり、この項目はJR東日本・都営の場合に存在するため定義した
    /**
     * 発生路線名称
     */
    @SerializedName("odpt:railwayTitle")
    var railwayTitle: String? = null
    /**
     * 復旧見込み時刻、ただし配信されない場合が多い
     */
    @SerializedName("odpt:resumeEstimate")
    var resumeEstimate: ZonedDateTime? = null
    /**
     * 発生場所起点の駅ID。取得不可能な場合は省略
     * JR東日本の場合のみ"odpt:stationFromTitle"で定義、それ以外は"odpt:stationFrom"で定義
     * @see OdptStationResponse.sameAs
     */
    @SerializedName(value = "stationFrom", alternate = arrayOf("odpt:stationFrom", "odpt:stationFromTitle"))
    var stationFrom: OdptStation? = null
    /**
     * 発生場所終点の駅ID。取得不可能な場合は省略
     * JR東日本の場合のみ"odpt:stationToTitle"で定義、それ以外は"odpt:stationTo"で定義
     * @see OdptStationResponse.sameAs
     */
    @SerializedName(value = "stationTo", alternate = arrayOf("odpt:stationTo", "odpt:stationToTitle"))
    var stationTo: OdptStation? = null
    /**
     * 発生時刻。平常運転時には情報取得日時が入る場合があり、odpt:trainInformationText が変更されない場合でもodpt:timeOfOrigin が更新される場合がある。
     * API仕様ではrequiredであるが、一部事業者（例：都営）で送信されないためoptionalにしている
     */
    @SerializedName("odpt:timeOfOrigin")
    var timeOfOrigin: ZonedDateTime? = null
    /**
     * 発生エリア。取得不可能な場合は省略
     */
    @SerializedName("odpt:trainInformationArea")
    var trainInformationArea: String? = null
    /**
     * 発生理由。取得不可能な場合は省略
     * JR東日本の場合のみ"odpt:trainInformationCauseTitle"で定義、それ以外は"odpt:trainInformationCause"で定義
     */
    @SerializedName(value = "trainInformationCause", alternate = arrayOf("odpt:trainInformationCause", "odpt:trainInformationCauseTitle"))
    var trainInformationCause: String? = null
    /**
     * 鉄道種類。取得不可能な場合は省略
     */
    @SerializedName("odpt:trainInformationKind")
    var trainInformationKind: String? = null
    /**
     * 列車の運転方向。取得不可能な場合は省略
     * JR東日本の場合のみ"odpt:trainInformationLineTitle"で定義、それ以外は"odpt:trainInformationLine"で定義
     */
    @SerializedName(value = "trainInformationLine", alternate = arrayOf("odpt:trainInformationLine", "odpt:trainInformationLineTitle"))
    var trainInformationLine: String? = null
    /**
     * 発生区間。取得不可能な場合は省略
     */
    @SerializedName("odpt:trainInformationRange")
    var trainInformationRange: String? = null
    /**
     * 平常時は省略。運行情報が存在する場合は「運行情報あり」を格納。遅延などの情報を取得可能な場合は、「遅延」等のテキストを格納
     * JR東日本の場合のみ"odpt:trainInformationStatusTitle"でも定義、それ以外は"odpt:trainInformationStatus"だけで定義
     */
    @SerializedName(value = "trainInformationStatus", alternate = arrayOf("odpt:trainInformationStatus", "odpt:trainInformationStatusTitle"))
    var trainInformationStatus: String? = null
    /**
     * 運行情報テキスト
     */
    @SerializedName("odpt:trainInformationText")
    var trainInformationText: String = ""
    /**
     * 振替路線の路線ID一覧
     * @see OdptRailwayResponse.sameAs
     */
    @SerializedName("odpt:transferRailways")
    var transferRailways: List<OdptRailway>? = null
}
