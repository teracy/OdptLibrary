package me.kwsk.odptlibrary.core.api

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.train.*
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 鉄道API
 */
@Singleton
class OdptTrainApiClient @Inject constructor(private val consumerKey: String, private val odptTrainApiService: OdptTrainApiService) {
    /**
     * 駅別乗降人員取得
     * @param odptId データに付与された固有識別子
     * @param operator 事業者情報 odpt:Operatorを示すID（odpt:Operatorのowl:sameAs）
     * @param railway 路線情報 odpt:Railwayを示すID（odpt:Railwayのowl:sameAs）
     * @param station 駅情報 odpt:Stationを示すID（odpt:Stationのowl:sameAs）
     * @param surveyYear 調査年（西暦）
     * @param sameAs データに付与された固有識別子の別名
     * @return 駅別乗降人員リスト
     */
    fun getPassengerSurvey(odptId: String? = null, operator: String? = null, railway: String? = null,
                           station: String? = null, surveyYear: Int? = null, sameAs: String? = null): Single<List<OdptPassengerSurveyResponse>> {
        return odptTrainApiService.getPassengerSurvey(consumerKey, odptId, operator, railway, station, surveyYear, sameAs)
    }

    /**
     * 路線情報取得
     * @param odptId データに付与された固有識別子
     * @param title 路線名（e.g. 小田原線、京王線、相模原線）
     * @param lineCode 路線コード、路線シンボル表記を格納（e.g. 小田原線 => OH、丸ノ内線=>M）
     * @param operator 事業者ID（e.g. 小田急電鉄: odpt.Operator:Odakyu）
     * @param sameAs データに付与された固有識別子の別名
     * @return 路線情報リスト
     */
    fun getRailway(odptId: String? = null, title: String? = null, lineCode: String? = null,
                   operator: String? = null, sameAs: String? = null): Single<List<OdptRailwayResponse>> {
        return odptTrainApiService.getRailway(consumerKey, odptId, title, lineCode, operator, sameAs)
    }

    /**
     * 運賃情報取得
     * @param odptId データに付与された固有識別子
     * @param fromStation 起点となる駅のID
     * @param operator 事業者ID（e.g. 小田急電鉄: odpt.Operator:Odakyu）
     * @param toStation 着点となる駅のID
     * @param sameAs データに付与された固有識別子の別名
     * @return 運賃情報リスト
     */
    fun getRailwayFare(odptId: String? = null, fromStation: String? = null, operator: String? = null,
                       toStation: String? = null, sameAs: String? = null): Single<List<OdptRailwayFareResponse>> {
        return odptTrainApiService.getRailwayFare(consumerKey, odptId, fromStation, operator, toStation, sameAs)
    }

    /**
     * 駅情報取得
     * @param odptId データに付与された固有識別子
     * @param title 駅名（e.g. 東京、新宿、上野）
     * @param operator 事業者ID（e.g. 小田急電鉄: odpt.Operator:Odakyu）
     * @param railway 駅が存在する路線ID（e.g. 小田急小田原線: odpt.Railway:Odakyu.Odawara）
     * @param stationCode 駅ナンバリング（e.g. OH01=小田急新宿駅）
     * @param sameAs データに付与された固有識別子の別名
     * @return 駅情報リスト
     */
    fun getStation(odptId: String? = null, title: String? = null, operator: String? = null,
                   railway: String? = null, stationCode: String? = null, sameAs: String? = null): Single<List<OdptStationResponse>> {
        return odptTrainApiService.getStation(consumerKey, odptId, title, operator, railway, stationCode, sameAs)
    }

    /**
     * 駅時刻表取得
     * @param odptId データに付与された固有識別子
     * @param date 特定日付の時刻表を取得
     * @param calendar 実施日 odpt:Calendarを示すID（odpt:Calendarのowl:sameAs）
     * @param operator 事業者情報 odpt:Operatorを示すID（odpt:Operatorのowl:sameAs）
     * @param railDirection 進行方向 odpt:RailDirectionを示すID（odpt:RailDirectionのowl:sameAs）
     * @param railway 路線情報 odpt:Railwayを示すID（odpt:Railwayのowl:sameAs）
     * @param station 駅情報 odpt:Stationを示すID（odpt:Stationのowl:sameAs）
     * @param sameAs データに付与された固有識別子の別名
     * @return 駅時刻表リスト
     */
    fun getStationTimetable(odptId: String? = null, date: String? = null, calendar: String? = null,
                            operator: String? = null, railDirection: String? = null,
                            railway: String? = null, station: String? = null, sameAs: String? = null): Single<List<OdptStationTimetableResponse>> {
        return odptTrainApiService.getStationTimetable(consumerKey, odptId, date, calendar, operator, railDirection, railway, station, sameAs)
    }

    // NOTE: sameAsはAPI仕様にはないが、応答に含まれている以上利用できると判断し、実際利用できたため追加した
    /**
     * 列車情報取得
     * @param operator 列車情報を配信する事業者のID
     * @param railway 当該列車が運行している路線のID
     * @param sameAs データに付与された固有識別子の別名
     * @return 列車情報リスト
     */
    fun getTrain(operator: String? = null, railway: String? = null, sameAs: String? = null): Single<List<OdptTrainResponse>> {
        return odptTrainApiService.getTrain(consumerKey, operator, railway, sameAs)
    }

    /**
     * 運行情報取得
     * @param operator 運行情報を配信する事業者のID
     * @param railway 運行情報が発生した路線のID
     * @return 運行情報リスト
     */
    fun getTrainInformation(operator: String? = null, railway: String? = null): Single<List<OdptTrainInformationResponse>> {
        return odptTrainApiService.getTrainInformation(consumerKey, operator, railway)
    }

    // NOTE: optionalになっている項目でも極力強制（できれば列車IDだけでも）しないとUXに大きく影響するので注意
    /**
     * 列車時刻表取得
     * @param odptId 固有識別子
     * @param calendar 特定のカレンダー情報ID
     * @param operator 運行事業者のID
     * @param railway 路線のID
     * @param train 該当する列車ID
     * @param trainNumber 列車番号、運行管理に用いられる運用番号
     * @param trainType 列車種別ID
     * @param sameAs 固有識別子別名
     * @return 列車時刻表リスト
     */
    fun getTrainTimetable(odptId: String? = null, calendar: String? = null, operator: String? = null,
                          railway: String? = null, train: String? = null, trainNumber: String? = null,
                          trainType: String? = null, sameAs: String? = null): Single<List<OdptTrainTimetableResponse>> {
        return odptTrainApiService.getTrainTimetable(consumerKey, odptId, calendar, operator, railway, train, trainNumber, trainType, sameAs)
    }
}

interface OdptTrainApiService {
    /**
     * 駅別乗降人員取得
     * @param consumerKey APIアクセス用のアクセストークン
     * @param odptId データに付与された固有識別子
     * @param operator 事業者情報 odpt:Operatorを示すID（odpt:Operatorのowl:sameAs）
     * @param railway 路線情報 odpt:Railwayを示すID（odpt:Railwayのowl:sameAs）
     * @param station 駅情報 odpt:Stationを示すID（odpt:Stationのowl:sameAs）
     * @param surveyYear 調査年（西暦）
     * @param sameAs データに付与された固有識別子の別名
     * @return 駅別乗降人員リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:PassengerSurvey")
    fun getPassengerSurvey(@Query("acl:consumerKey") consumerKey: String,
                           @Query("@id") odptId: String?,
                           @Query("odpt:operator") operator: String?,
                           @Query("odpt:railway") railway: String?,
                           @Query("odpt:station") station: String?,
                           @Query("odpt:surveyYear") surveyYear: Int?,
                           @Query("owl:sameAs") sameAs: String?): Single<List<OdptPassengerSurveyResponse>>

    /**
     * 路線情報取得
     * @param consumerKey APIアクセス用のアクセストークン
     * @param odptId データに付与された固有識別子
     * @param title 路線名（e.g. 小田原線、京王線、相模原線）
     * @param lineCode 路線コード、路線シンボル表記を格納（e.g. 小田原線 => OH、丸ノ内線=>M）
     * @param operator 事業者ID（e.g. 小田急電鉄: odpt.Operator:Odakyu）
     * @param sameAs データに付与された固有識別子の別名
     * @return 路線情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:Railway")
    fun getRailway(@Query("acl:consumerKey") consumerKey: String,
                   @Query("@id") odptId: String?,
                   @Query("dc:title") title: String?,
                   @Query("odpt:lineCode") lineCode: String?,
                   @Query("odpt:operator") operator: String?,
                   @Query("owl:sameAs") sameAs: String?): Single<List<OdptRailwayResponse>>

    /**
     * 運賃情報取得
     * @param consumerKey APIアクセス用のアクセストークン
     * @param odptId データに付与された固有識別子
     * @param fromStation 起点となる駅のID
     * @param operator 事業者ID（e.g. 小田急電鉄: odpt.Operator:Odakyu）
     * @param toStation 着点となる駅のID
     * @param sameAs データに付与された固有識別子の別名
     * @return 運賃情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:RailwayFare")
    fun getRailwayFare(@Query("acl:consumerKey") consumerKey: String,
                       @Query("@id") odptId: String?,
                       @Query("odpt:fromStation") fromStation: String?,
                       @Query("odpt:operator") operator: String?,
                       @Query("odpt:toStation") toStation: String?,
                       @Query("owl:sameAs") sameAs: String?): Single<List<OdptRailwayFareResponse>>

    /**
     * 駅情報取得
     * @param consumerKey APIアクセス用のアクセストークン
     * @param odptId データに付与された固有識別子
     * @param title 駅名（e.g. 東京、新宿、上野）
     * @param operator 事業者ID（e.g. 小田急電鉄: odpt.Operator:Odakyu）
     * @param railway 駅が存在する路線ID（e.g. 小田急小田原線: odpt.Railway:Odakyu.Odawara）
     * @param stationCode 駅ナンバリング（e.g. OH01=小田急新宿駅）
     * @param sameAs データに付与された固有識別子の別名
     * @return 駅情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:Station")
    fun getStation(@Query("acl:consumerKey") consumerKey: String,
                   @Query("@id") odptId: String?,
                   @Query("dc:title") title: String?,
                   @Query("odpt:operator") operator: String?,
                   @Query("odpt:railway") railway: String?,
                   @Query("odpt:stationCode") stationCode: String?,
                   @Query("owl:sameAs") sameAs: String?): Single<List<OdptStationResponse>>

    /**
     * 駅時刻表取得
     * @param consumerKey APIアクセス用のアクセストークン
     * @param odptId データに付与された固有識別子
     * @param date 特定日付の時刻表を取得
     * @param calendar 実施日 odpt:Calendarを示すID（odpt:Calendarのowl:sameAs）
     * @param operator 事業者情報 odpt:Operatorを示すID（odpt:Operatorのowl:sameAs）
     * @param railDirection 進行方向 odpt:RailDirectionを示すID（odpt:RailDirectionのowl:sameAs）
     * @param railway 路線情報 odpt:Railwayを示すID（odpt:Railwayのowl:sameAs）
     * @param station 駅情報 odpt:Stationを示すID（odpt:Stationのowl:sameAs）
     * @param sameAs データに付与された固有識別子の別名
     * @return 駅時刻表リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:StationTimetable")
    fun getStationTimetable(@Query("acl:consumerKey") consumerKey: String,
                            @Query("@id") odptId: String?,
                            @Query("dc:date") date: String?,
                            @Query("odpt:calendar") calendar: String?,
                            @Query("odpt:operator") operator: String?,
                            @Query("odpt:railDirection") railDirection: String?,
                            @Query("odpt:railway") railway: String?,
                            @Query("odpt:station") station: String?,
                            @Query("owl:sameAs") sameAs: String?): Single<List<OdptStationTimetableResponse>>

    // NOTE: sameAsはAPI仕様にはないが、応答に含まれている以上利用できると判断し、実際利用できたため追加した
    /**
     * 列車情報取得
     * @param consumerKey APIアクセス用のアクセストークン
     * @param operator 列車情報を配信する事業者のID
     * @param railway 当該列車が運行している路線のID
     * @return 列車情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:Train")
    fun getTrain(@Query("acl:consumerKey") consumerKey: String,
                 @Query("odpt:operator") operator: String?,
                 @Query("odpt:railway") railway: String?,
                 @Query("owl:sameAs") sameAs: String?): Single<List<OdptTrainResponse>>

    /**
     * 運行情報取得
     * @param consumerKey APIアクセス用のアクセストークン
     * @param operator 運行情報を配信する事業者のID
     * @param railway 運行情報が発生した路線のID
     * @return 運行情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:TrainInformation")
    fun getTrainInformation(@Query("acl:consumerKey") consumerKey: String,
                            @Query("odpt:operator") operator: String?,
                            @Query("odpt:railway") railway: String?): Single<List<OdptTrainInformationResponse>>

    /**
     * 列車時刻表取得
     * @param odptId 固有識別子
     * @param consumerKey APIアクセス用のアクセストークン
     * @param calendar 特定のカレンダー情報ID
     * @param operator 運行事業者のID
     * @param railway 路線のID
     * @param train 該当する列車ID
     * @param trainNumber 列車番号、運行管理に用いられる運用番号
     * @param trainType 列車種別ID
     * @param sameAs 固有識別子別名
     * @return 列車時刻表リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:TrainTimetable")
    fun getTrainTimetable(@Query("acl:consumerKey") consumerKey: String,
                          @Query("@id") odptId: String?,
                          @Query("odpt:calendar") calendar: String?,
                          @Query("odpt:operator") operator: String?,
                          @Query("odpt:railway") railway: String?,
                          @Query("odpt:train") train: String?,
                          @Query("odpt:trainNumber") trainNumber: String?,
                          @Query("odpt:trainType") trainType: String?,
                          @Query("owl:sameAs") sameAs: String?): Single<List<OdptTrainTimetableResponse>>
}
