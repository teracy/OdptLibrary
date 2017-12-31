package me.kwsk.odptlibrary.repository.train

import io.reactivex.Single
import io.reactivex.functions.Function
import me.kwsk.odptlibrary.core.api.OdptTrainApiClient
import me.kwsk.odptlibrary.core.api.code.OPERATOR_TOKYO_METRO
import me.kwsk.odptlibrary.core.api.code.OPERATOR_TWR
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import me.kwsk.odptlibrary.core.api.common.OdptRailway
import me.kwsk.odptlibrary.core.api.train.OdptRailwayResponse
import javax.inject.Inject

/**
 * 路線情報Repository
 */
interface RailwayRepository {
    // NOTE: 参加している全鉄道事業者で取得可能
    /**
     * 路線情報取得（特定の事業者指定）
     * @param operator 事業者ID :required
     * @return 路線情報リスト
     */
    fun getRailway(operator: OdptOperator): Single<List<OdptRailwayResponse>>

    /**
     * 路線情報取得（特定の路線指定）
     * @param railway 路線ID :required
     * @return 路線情報リスト
     */
    fun getRailway(railway: OdptRailway): Single<List<OdptRailwayResponse>>
}

/**
 * 路線情報RemoteRepository
 */
class RailwayRemoteRepository @Inject constructor(private val odptTrainApiClient: OdptTrainApiClient) : RailwayRepository {
    override fun getRailway(operator: OdptOperator): Single<List<OdptRailwayResponse>> {
        return odptTrainApiClient.getRailway(operator = operator.id)
                .flattenAsObservable { it }
                .map { mapper().apply(it) }
                .toList()
    }

    override fun getRailway(railway: OdptRailway): Single<List<OdptRailwayResponse>> {
        // FIXME: 2017/12/27 現時点で、路線情報APIに限り東京臨海高速鉄道りんかい線のodpt.railwayの値が"odpt.Railway:odpt.Station:TWR.Rinkai"なのでinvalidである。正しくは"odpt.Railway:TWR.Rinkai"のはず
        return odptTrainApiClient.getRailway(sameAs = railway.id)
                .flattenAsObservable { it }
                .map { mapper().apply(it) }
                .toList()
    }

    private fun mapper(): Function<OdptRailwayResponse, OdptRailwayResponse> {
        // 東京メトロと東京臨海高速鉄道の場合かつ、末尾に「線」がない場合のみ、路線名の末尾に「線」を付加
        return Function {
            if ((it.operator?.id == OPERATOR_TOKYO_METRO || it.operator?.id == OPERATOR_TWR)
                    && !Regex("線$").matches(it.title))
                it.apply { this.title += "線" }
            else
                it
        }
    }
}