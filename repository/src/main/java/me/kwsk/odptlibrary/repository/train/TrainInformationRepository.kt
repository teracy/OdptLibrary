package me.kwsk.odptlibrary.repository.train

import io.reactivex.Observable
import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.OdptTrainApiClient
import me.kwsk.odptlibrary.core.api.code.*
import me.kwsk.odptlibrary.core.api.common.OdptRailway
import me.kwsk.odptlibrary.core.api.train.OdptTrainInformationResponse
import me.kwsk.odptlibrary.repository.InvalidOdptOperationException
import org.threeten.bp.Duration
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

/**
 * 運行情報Repository
 */
interface TrainInformationRepository {
    // NOTE: JR東日本・京急電鉄・京王電鉄・小田急電鉄・都営・東京メトロ・東急電鉄・臨海高速鉄道のみ可能
    // FIXME: 京急電鉄は運行情報を提供予定であるが、現状「データがないから空：JR東日本型」なのか「APIが使えないから空」なのかわからないため実装保留中
    // FIXME: 京王電鉄は運行情報を提供予定であるが、現状「データがないから空：JR東日本型」なのか「APIが使えないから空」なのかわからないため実装保留中
    // FIXME: 小田急電鉄は運行情報を提供予定であるが、現状「データがないから空：JR東日本型」なのか「APIが使えないから空」なのかわからないため実装保留中
    // FIXME: 東急電鉄は運行情報を提供予定であるが、現状「データがないから空：JR東日本型」なのか「APIが使えないから空」なのかわからないため実装保留中
    // FIXME: 東京臨海高速鉄道は運行情報を提供予定であるが、現状「データがないから空：JR東日本型」なのか「APIが使えないから空」なのかわからないため実装保留中

    /**
     * JR東日本運行情報取得
     * @param railway 路線IDフィルター :optional、入力がある場合は対象の路線の情報のみ返す。入力がない場合は全路線のデータを返す
     * @param minutes 直近何分のデータまで許容するかを指定する :option、指定がない場合は直近15分
     * @param onlyRecent 直近の情報のみ取得するか :optional、指定がない場合はtrue
     * @return 運行情報リスト：JR東日本の場合、平常運行の路線のデータは取得されない
     */
    fun getJREastTrainInformation(railway: OdptRailway? = null, minutes: Long? = 15L, onlyRecent: Boolean? = true): Observable<OdptTrainInformationResponse>

    /**
     * 都営運行情報取得
     * @param railway 路線IDフィルター :optional、入力がある場合は対象の路線の情報のみ返す。入力がない場合は全路線のデータを返す
     * @return 運行情報リスト：都営の場合、運行支障のある路線のデータは取得されない
     */
    fun getToeiTrainInformation(railway: OdptRailway? = null): Observable<OdptTrainInformationResponse>

    /**
     * 運行支障のある都営路線取得
     * @return 運行支障のある都営路線ID（運行情報APIにおいて、路線に都営を指定すると「運行支障がない」路線のみ通常運行であるというデータが返ってくる仕様のため）
     */
    fun getProblematicToeiRailway(): Observable<OdptRailway>

    /**
     * 対象の都営路線が平常運転か否か
     * @param railway 対象の都営路線ID
     * @return true: 平常運転である、false: 運行支障中である
     */
    fun isNormally(railway: OdptRailway): Single<Boolean>

    /**
     * 東京メトロ運行情報取得
     * @param railway 路線IDフィルター :optional、入力がある場合は対象の路線の情報のみ返す。入力がない場合は全路線のデータを返す（運行支障フィルターと併用可）
     * @param statusFilter 運行支障フィルター :optional、trueの場合は運行支障がある路線のみ取得する
     * @return 運行情報リスト
     */
    fun getTokyoMetroTrainInformation(railway: OdptRailway? = null, statusFilter: Boolean? = false): Observable<OdptTrainInformationResponse>
}

/**
 * 運行情報RemoteRepository
 */
class TrainInformationRemoteRepository @Inject constructor(private val odptTrainApiClient: OdptTrainApiClient) : TrainInformationRepository {
    override fun getJREastTrainInformation(railway: OdptRailway?, minutes: Long?, onlyRecent: Boolean?): Observable<OdptTrainInformationResponse> {
        // NOTE: JR東日本の場合、最新情報だけでなく履歴情報も「古い順に」当日分全て返ってくる。逆に運行支障がない路線はデータが取れない
        // ->データがあってもそれが現在有効かどうかの判断が必要
        val now = ZonedDateTime.now()
        // 直近1時間以内のデータでrailway毎にまとめた上で日付降順にソートし、最新1件のみ取得する
        return odptTrainApiClient.getTrainInformation(operator = OPERATOR_JR_EAST)
                .flattenAsObservable { it }
                .filter { Duration.between(it.date, now).toMinutes() <= minutes ?: 15L }
                .filter { it.railway != null }
                .filter { if (railway != null) it.railway!!.id == railway.id else true }
                .groupBy { it.railway!!.id }
                .flatMap { it.sorted(compareByDescending { it.date }).let { if (onlyRecent != false) it.take(1) else it } }
    }

    override fun getToeiTrainInformation(railway: OdptRailway?): Observable<OdptTrainInformationResponse> {
        // NOTE: 都営の場合、「運行支障がない」路線のみ通常運行であるというデータが返ってくる
        return odptTrainApiClient.getTrainInformation(operator = OPERATOR_TOEI)
                .flattenAsObservable { it }
                .filter { if (railway != null) it.railway?.id == railway.id else true }
    }

    override fun getProblematicToeiRailway(): Observable<OdptRailway> {
        val map = mutableMapOf<String, Boolean>()
        RailwayToei.values().forEach { map.put(it.id, false) }
        return getToeiTrainInformation()
                .filter { it.railway?.id != null }
                .collectInto(map, { t1, t2 -> t1.put(t2.railway!!.id, true) })
                .toObservable()
                .flatMapIterable { it.entries }
                // false、つまり取得できなかった路線のみ抽出
                .filter { !it.value }
                .map { OdptRailway(it.key) }
    }

    /**
     * 対象の都営路線が平常運転か否か
     * @param railway 対象の都営路線ID
     * @return true: 平常運転である、false: 運行支障中である（都営以外の路線IDを与えた場合はSingle.error([InvalidOdptOperationException])を返す）
     */
    override fun isNormally(railway: OdptRailway): Single<Boolean> {
        if (!RailwayToei.values().map { it.id }.contains(railway.id)) {
            return Single.error(InvalidOdptOperationException())
        }
        // NOTE: 都営の場合、「運行支障がない」路線のみ通常運行であるというデータが返ってくる
        // ->対象路線のデータが返ってこなければ運行支障中である
        return getToeiTrainInformation()
                .filter { it.railway?.id == railway.id }
                .isEmpty
    }

    override fun getTokyoMetroTrainInformation(railway: OdptRailway?, statusFilter: Boolean?): Observable<OdptTrainInformationResponse> {
        // NOTE: 東京メトロの場合、運行支障があろうとなかろうと直近の運行情報が返ってくる
        return odptTrainApiClient.getTrainInformation(operator = OPERATOR_TOKYO_METRO)
                .flattenAsObservable { it }
                .filter { if (railway != null) it.railway?.id == railway.id else true }
                .filter { statusFilter != true || it.trainInformationStatus != null }
    }
}