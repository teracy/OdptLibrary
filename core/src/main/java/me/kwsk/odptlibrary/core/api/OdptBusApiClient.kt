package me.kwsk.odptlibrary.core.api

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.bus.*
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Inject

/**
 * バスAPI
 */
class OdptBusApiClient @Inject constructor(private val consumerKey: String, private val odptBusApiService: OdptBusApiService) {
    /**
     * バス情報取得
     * @param busRoutePattern バス路線ID [OdptBusRoutePatternResponse.sameAs]
     * @param fromBusStopPole バスが出発したバス停のID [OdptBusStopPoleResponse.sameAs]
     * @param operator バス情報を配信する事業者のID [OdptOperator]
     * @param toBusStopPole バスが向かっているバス停のID [OdptBusStopPoleResponse.sameAs]
     * @param sameAs バスID [OdptBusResponse.sameAs]
     * @return バス情報リスト
     */
    fun getBus(busRoutePattern: String? = null, fromBusStopPole: String? = null, operator: String? = null,
               toBusStopPole: String? = null, sameAs: String? = null): Single<List<OdptBusResponse>> {
        return odptBusApiService.getBus(consumerKey, busRoutePattern, fromBusStopPole, operator, toBusStopPole, sameAs)
    }

    // NOTE: 仕様書では"odpt:operator"はrequiredになっているが、@id/odpt:busroutePattern/owl:sameAsを利用する場合はodpt:operator要らないはずで、実際に送らなくても情報が取れるためnullableにしている
    /**
     * バス時刻表取得
     * @param odptId 識別子 [OdptBusTimetableResponse.odptId]
     * @param title バス路線名 [OdptBusTimetableResponse.title]
     * @param busRoutePattern バス路線ID [OdptBusRoutePatternResponse.sameAs]
     * @param calendar 日付・曜日情報
     * @param operator バス時刻表を配信する事業者のID [OdptOperator]
     * @param sameAs バス時刻表ID [OdptBusTimetableResponse.sameAs]
     * @return バス時刻表リスト
     */
    fun getBusTimetable(odptId: String? = null, title: String? = null, busRoutePattern: String? = null,
                        calendar: String? = null, operator: String? = null, sameAs: String? = null): Single<List<OdptBusTimetableResponse>> {
        return odptBusApiService.getBusTimetable(consumerKey, odptId, title, busRoutePattern, calendar, operator, sameAs)
    }

    /**
     * バス路線情報取得
     * @param odptId 識別子 [OdptBusRoutePatternResponse.odptId]
     * @param title バス停名 [OdptBusStopPoleResponse.title]
     * @param busroute バス系統ID [OdptBusRoutePatternResponse.busRoute]
     * @param operator バス路線情報を配信する事業者のID [OdptOperator]
     * @param sameAs バス路線情報ID [OdptBusRoutePatternResponse.sameAs]
     * @return バス路線情報リスト
     */
    fun getBusRoutePattern(odptId: String? = null, title: String? = null, busroute: String? = null,
                           operator: String? = null, sameAs: String? = null): Single<List<OdptBusRoutePatternResponse>> {
        return odptBusApiService.getBusRoutePattern(consumerKey, odptId, title, busroute, operator, sameAs)
    }

    /**
     * バス運賃情報取得
     * @param odptId 識別子 [OdptBusRoutePatternFareResponse.odptId]
     * @param childIcCardFare ICカード利用時の子供運賃
     * @param childTicketFare 切符利用時の子供運賃
     * @param fromBuStopPole 乗車バス停標柱 [OdptBusStopPoleResponse.sameAs]
     * @param icCardFare ICカード利用時の運賃
     * @param operator バス路線情報を配信する事業者のID [OdptOperator]
     * @param ticketFare 切符利用時の運賃
     * @param toBusStopPole 降車バス停標柱 [OdptBusStopPoleResponse.sameAs]
     * @param sameAs バス運賃情報ID [OdptBusRoutePatternFareResponse.sameAs]
     * @return バス運賃情報リスト
     */
    fun getBusRoutePatternFare(odptId: String? = null, childIcCardFare: Int? = null, childTicketFare: Int? = null,
                               fromBuStopPole: String? = null, icCardFare: Int? = null, operator: String? = null,
                               ticketFare: Int? = null, toBusStopPole: String? = null, sameAs: String? = null): Single<List<OdptBusRoutePatternFareResponse>> {
        return odptBusApiService.getBusRoutePatternFare(consumerKey, odptId, childIcCardFare, childTicketFare, fromBuStopPole, icCardFare, operator, ticketFare, toBusStopPole, sameAs)
    }

    /**
     * バス停情報取得
     * @param odptId 識別子 [OdptBusStopPoleResponse.odptId]
     * @param title バス停名 [OdptBusStopPoleResponse.title]
     * @param busRoutePattern バス路線ID [OdptBusRoutePatternResponse.sameAs]
     * @param busStopPoleNumber 標柱番号 [OdptBusStopPoleResponse.busStopPoleNumber]
     * @param operator バス停情報を配信する事業者のID [OdptOperator]
     * @param sameAs バス停ID [OdptBusStopPoleResponse.sameAs]
     * @return バス停情報リスト
     */
    fun getBusStopPole(odptId: String? = null,
                       title: String? = null,
                       busRoutePattern: String? = null,
                       busStopPoleNumber: String? = null,
                       operator: String? = null,
                       sameAs: String? = null): Single<List<OdptBusStopPoleResponse>> {
        return odptBusApiService.getBusStopPole(consumerKey, odptId, title, busRoutePattern, busStopPoleNumber, operator, sameAs)
    }

    /**
     * バス停時刻表取得
     * @param odptId 識別子 [OdptBusStopPoleResponse.odptId]
     * @param date データ生成時刻 [OdptBusStopPoleResponse.date]
     * @param busDirection バス方面ID [OdptBusStopPoleTimetableResponse.busDirection]
     * @param busRoute バス路線ID [OdptBusStopPoleTimetableResponse.busRoute]
     * @param busStopPole バス停標柱ID [OdptBusStopPoleResponse.sameAs]
     * @param calendar 日付・曜日情報
     * @param operator バス停情報を配信する事業者のID [OdptOperator]
     * @param sameAs バス停時刻表ID [OdptBusStopPoleTimetableResponse.sameAs]
     * @return バス停時刻表リスト
     */
    fun getBusStopPoleTimetable(odptId: String? = null, date: String? = null, busDirection: String? = null,
                                busRoute: String? = null, busStopPole: String? = null, calendar: String? = null,
                                operator: String? = null, sameAs: String? = null): Single<List<OdptBusStopPoleTimetableResponse>> {
        return odptBusApiService.getBusStopPoleTimetable(consumerKey, odptId, date, busDirection, busRoute, busStopPole, calendar, operator, sameAs)
    }
}

interface OdptBusApiService {
    /**
     * バス情報取得
     * @param consumerKey APIアクセス用のアクセストークン
     * @param busRoutePattern バス路線ID [OdptBusRoutePatternResponse.sameAs]
     * @param fromBusStopPole バスが出発したバス停のID [OdptBusStopPoleResponse.sameAs]
     * @param operator バス情報を配信する事業者のID [OdptOperator]
     * @param toBusStopPole バスが向かっているバス停のID [OdptBusStopPoleResponse.sameAs]
     * @param sameAs バスID [OdptBusResponse.sameAs]
     * @return バス情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:Bus")
    fun getBus(@Query("acl:consumerKey") consumerKey: String,
               @Query("odpt:busroutePattern") busRoutePattern: String?,
               @Query("odpt:fromBusstopPole") fromBusStopPole: String?,
               @Query("odpt:operator") operator: String?,
               @Query("odpt:toBusstopPole") toBusStopPole: String?,
               @Query("owl:sameAs") sameAs: String?): Single<List<OdptBusResponse>>

    // NOTE: 仕様書では"odpt:operator"はrequiredになっているが、@id/odpt:busroutePattern/owl:sameAsを利用する場合はodpt:operator要らないはずで、実際に送らなくても情報が取れるためnullableにしている
    /**
     * バス時刻表取得
     * @param consumerKey APIアクセス用のアクセストークン
     * @param odptId 識別子 [OdptBusTimetableResponse.odptId]
     * @param title バス路線名 [OdptBusTimetableResponse.title]
     * @param busRoutePattern バス路線ID [OdptBusRoutePatternResponse.sameAs]
     * @param calendar 日付・曜日情報
     * @param operator バス時刻表を配信する事業者のID [OdptOperator]
     * @param sameAs バス時刻表ID [OdptBusTimetableResponse.sameAs]
     * @return バス時刻表リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:BusTimetable")
    fun getBusTimetable(@Query("acl:consumerKey") consumerKey: String,
                        @Query("@id") odptId: String?,
                        @Query("dc:title") title: String?,
                        @Query("odpt:busroutePattern") busRoutePattern: String?,
                        @Query("odpt:calendar") calendar: String?,
                        @Query("odpt:operator") operator: String?,
                        @Query("owl:sameAs") sameAs: String?): Single<List<OdptBusTimetableResponse>>

    /**
     * バス路線情報取得
     * @param consumerKey APIアクセス用のアクセストークン
     * @param odptId 識別子 [OdptBusRoutePatternResponse.odptId]
     * @param title バス停名 [OdptBusStopPoleResponse.title]
     * @param busroute バス系統ID [OdptBusRoutePatternResponse.busRoute]
     * @param operator バス路線情報を配信する事業者のID [OdptOperator]
     * @param sameAs バス路線情報ID [OdptBusRoutePatternResponse.sameAs]
     * @return バス路線情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:BusroutePattern")
    fun getBusRoutePattern(@Query("acl:consumerKey") consumerKey: String,
                           @Query("@id") odptId: String?,
                           @Query("dc:title") title: String?,
                           @Query("odpt:busroute") busroute: String?,
                           @Query("odpt:operator") operator: String?,
                           @Query("owl:sameAs") sameAs: String?): Single<List<OdptBusRoutePatternResponse>>

    /**
     * バス運賃情報取得
     * @param consumerKey APIアクセス用のアクセストークン
     * @param odptId 識別子 [OdptBusRoutePatternFareResponse.odptId]
     * @param childIcCardFare ICカード利用時の子供運賃
     * @param childTicketFare 切符利用時の子供運賃
     * @param fromBuStopPole 乗車バス停標柱 [OdptBusStopPoleResponse.sameAs]
     * @param icCardFare ICカード利用時の運賃
     * @param operator バス路線情報を配信する事業者のID [OdptOperator]
     * @param ticketFare 切符利用時の運賃
     * @param toBusStopPole 降車バス停標柱 [OdptBusStopPoleResponse.sameAs]
     * @param sameAs バス運賃情報ID [OdptBusRoutePatternFareResponse.sameAs]
     * @return バス運賃情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:BusroutePatternFare")
    fun getBusRoutePatternFare(@Query("acl:consumerKey") consumerKey: String,
                               @Query("@id") odptId: String?,
                               @Query("odpt:childIcCardFare") childIcCardFare: Int?,
                               @Query("odpt:childTicketFare") childTicketFare: Int?,
                               @Query("odpt:fromBusstopPole") fromBuStopPole: String?,
                               @Query("odpt:icCardFare") icCardFare: Int?,
                               @Query("odpt:operator") operator: String?,
                               @Query("odpt:ticketFare") ticketFare: Int?,
                               @Query("odpt:toBusstopPole") toBusStopPole: String?,
                               @Query("owl:sameAs") sameAs: String?): Single<List<OdptBusRoutePatternFareResponse>>

    /**
     * バス停情報取得
     * @param consumerKey APIアクセス用のアクセストークン
     * @param odptId 識別子 [OdptBusStopPoleResponse.odptId]
     * @param title バス停名 [OdptBusStopPoleResponse.title]
     * @param busRoutePattern バス路線ID [OdptBusRoutePatternResponse.sameAs]
     * @param busStopPoleNumber 標柱番号 [OdptBusStopPoleResponse.busStopPoleNumber]
     * @param operator バス停情報を配信する事業者のID [OdptOperator]
     * @param sameAs バス停ID [OdptBusStopPoleResponse.sameAs]
     * @return バス停情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:BusstopPole")
    fun getBusStopPole(@Query("acl:consumerKey") consumerKey: String,
                       @Query("@id") odptId: String?,
                       @Query("dc:title") title: String?,
                       @Query("odpt:busroutePattern") busRoutePattern: String?,
                       @Query("odpt:busstopPoleNumber") busStopPoleNumber: String?,
                       @Query("odpt:operator") operator: String?,
                       @Query("owl:sameAs") sameAs: String?): Single<List<OdptBusStopPoleResponse>>

    /**
     * バス停時刻表取得
     * @param consumerKey APIアクセス用のアクセストークン
     * @param odptId 識別子 [OdptBusStopPoleResponse.odptId]
     * @param date データ生成時刻 [OdptBusStopPoleResponse.date]
     * @param busDirection バス方面ID [OdptBusStopPoleTimetableResponse.busDirection]
     * @param busRoute バス路線ID [OdptBusStopPoleTimetableResponse.busRoute]
     * @param busStopPole バス停標柱ID [OdptBusStopPoleResponse.sameAs]
     * @param calendar 日付・曜日情報
     * @param operator バス停情報を配信する事業者のID [OdptOperator]
     * @param sameAs バス停時刻表ID [OdptBusStopPoleTimetableResponse.sameAs]
     * @return バス停時刻表リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/odpt:BusstopPoleTimetable")
    fun getBusStopPoleTimetable(@Query("acl:consumerKey") consumerKey: String,
                                @Query("@id") odptId: String?,
                                @Query("dc:date") date: String?,
                                @Query("odpt:busDirection") busDirection: String?,
                                @Query("odpt:busroute") busRoute: String?,
                                @Query("odpt:busstopPole") busStopPole: String?,
                                @Query("odpt:calendar") calendar: String?,
                                @Query("odpt:operator") operator: String?,
                                @Query("owl:sameAs") sameAs: String?): Single<List<OdptBusStopPoleTimetableResponse>>
}