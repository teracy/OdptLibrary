package me.kwsk.odptlibrary.core.api

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.train.OdptRailwayResponse
import me.kwsk.odptlibrary.core.api.train.OdptStationResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

// FIXME: ドキュメントがまだ準備中なので確定次第修正
// FIXME: Client側ではList<String>?を引数にして、Serviceに与える際にカンマで連結して文字列にする
/**
 * 地物情報検索API
 */
@Singleton
class OdptPlaceApiClient @Inject constructor(private val consumerKey: String, private val odptPlaceApiService: OdptPlaceApiService) {
    /**
     * 鉄道路線情報取得
     * @param lon 検索範囲の中心点の経度、10進表記
     * @param lat 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @param odptId データに付与された固有識別子
     * @param title 路線名（e.g. 小田原線、京王線、相模原線）
     * @param lineCode 路線コード、路線シンボル表記を格納（e.g. 小田原線 => OH、丸ノ内線=>M）
     * @param operator 事業者ID（e.g. 小田急電鉄: odpt.Operator:Odakyu）
     * @param sameAs データに付与された固有識別子の別名
     * @return 鉄道路線情報リスト
     */
    fun getRailway(lon: Double, lat: Double, radius: Double,
                   odptId: String? = null, title: String? = null, lineCode: String? = null,
                   operator: String? = null, sameAs: String? = null): Single<List<OdptRailwayResponse>> {
        return odptPlaceApiService.getRailway("odpt:Railway", consumerKey, lon, lat, radius, odptId, title, lineCode, operator, sameAs)
    }

    /**
     * 駅情報取得
     * @param lon 検索範囲の中心点の経度、10進表記
     * @param lat 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @param odptId データに付与された固有識別子
     * @param title 駅名（e.g. 東京、新宿、上野）
     * @param operator 事業者ID（e.g. 小田急電鉄: odpt.Operator:Odakyu）
     * @param railway 駅が存在する路線ID（e.g. 小田急小田原線: odpt.Railway:Odakyu.Odawara）
     * @param stationCode 駅ナンバリング（e.g. OH01=小田急新宿駅）
     * @param sameAs データに付与された固有識別子の別名
     * @return 駅情報リスト
     */
    fun getStation(lon: Double, lat: Double, radius: Double,
                   odptId: String? = null, title: String? = null, operator: String? = null,
                   railway: String? = null, stationCode: String? = null, sameAs: String? = null): Single<List<OdptStationResponse>> {
        return odptPlaceApiService.getStation("odpt:Station", consumerKey, lon, lat, radius, odptId, title, operator, railway, stationCode, sameAs)
    }
}

interface OdptPlaceApiService {
    /**
     * 鉄道路線情報取得
     * @param odptType クラス指定（"odpt:Railway"固定）
     * @param consumerKey APIアクセス用のアクセストークン
     * @param lon 検索範囲の中心点の経度、10進表記
     * @param lat 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @param odptId データに付与された固有識別子
     * @param title 路線名（e.g. 小田原線、京王線、相模原線）
     * @param lineCode 路線コード、路線シンボル表記を格納（e.g. 小田原線 => OH、丸ノ内線=>M）
     * @param operator 事業者ID（e.g. 小田急電鉄: odpt.Operator:Odakyu）
     * @param sameAs データに付与された固有識別子の別名
     * @return 鉄道路線情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/places")
    fun getRailway(@Query("rdf:type") odptType: String,
                   @Query("acl:consumerKey") consumerKey: String,
                   @Query("lon") lon: Double,
                   @Query("lat") lat: Double,
                   @Query("radius") radius: Double,
                   @Query("@id") odptId: String?,
                   @Query("dc:title") title: String?,
                   @Query("odpt:lineCode") lineCode: String?,
                   @Query("odpt:operator") operator: String?,
                   @Query("owl:sameAs") sameAs: String?): Single<List<OdptRailwayResponse>>

    /**
     * 駅情報取得
     * @param odptType クラス指定（"odpt:Station"固定）
     * @param consumerKey APIアクセス用のアクセストークン
     * @param lon 検索範囲の中心点の経度、10進表記
     * @param lat 検索範囲の中心点の緯度、10進表記
     * @param radius 検索範囲の半径(m)
     * @param odptId データに付与された固有識別子
     * @param title 駅名（e.g. 東京、新宿、上野）
     * @param operator 事業者ID（e.g. 小田急電鉄: odpt.Operator:Odakyu）
     * @param railway 駅が存在する路線ID（e.g. 小田急小田原線: odpt.Railway:Odakyu.Odawara）
     * @param stationCode 駅ナンバリング（e.g. OH01=小田急新宿駅）
     * @param sameAs データに付与された固有識別子の別名
     * @return 駅情報リスト
     */
    @Headers("connection: close")
    @GET("/api/v4/places")
    fun getStation(@Query("rdf:type") odptType: String,
                   @Query("acl:consumerKey") consumerKey: String,
                   @Query("lon") lon: Double,
                   @Query("lat") lat: Double,
                   @Query("radius") radius: Double,
                   @Query("@id") odptId: String?,
                   @Query("dc:title") title: String?,
                   @Query("odpt:operator") operator: String?,
                   @Query("odpt:railway") railway: String?,
                   @Query("odpt:stationCode") stationCode: String?,
                   @Query("owl:sameAs") sameAs: String?): Single<List<OdptStationResponse>>
}