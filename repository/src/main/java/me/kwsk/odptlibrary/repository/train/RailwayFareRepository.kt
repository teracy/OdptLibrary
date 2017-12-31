package me.kwsk.odptlibrary.repository.train

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.OdptTrainApiClient
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import me.kwsk.odptlibrary.core.api.common.OdptRailwayFare
import me.kwsk.odptlibrary.core.api.common.OdptStation
import me.kwsk.odptlibrary.core.api.train.OdptRailwayFareResponse
import javax.inject.Inject

/**
 * 運賃情報Repository
 */
interface RailwayFareRepository {
    // NOTE: 京急電鉄・西武鉄道・都営・東武鉄道・東急電鉄・東京メトロ・臨海高速鉄道・ゆりかもめのみ可能
    // FIXME: 京急電鉄は運賃情報を提供予定であるが、現状まだ実装されていない模様
    // FIXME: 西武鉄道は運賃情報を提供予定であるが、現状まだ実装されていない模様
    // FIXME: 東急電鉄は運賃情報を提供予定であるが、現状まだ実装されていない模様
    // FIXME: 東京臨海高速鉄道は運賃情報を提供予定であるが、現状まだ実装されていない模様

    /**
     * 運賃情報取得（事業者のみ指定）
     * @param operator 事業者ID :required
     * @return 運賃情報リスト
     */
    fun getRailwayFare(operator: OdptOperator): Single<List<OdptRailwayFareResponse>>

    /**
     * 運賃情報取得（出発駅のみ指定、到着駅未指定可能）
     * @param fromStation 起点となる駅ID :required
     * @param toStation 着点となる駅ID :optional
     * @return 運賃情報リスト
     */
    fun getRailwayFare(fromStation: OdptStation, toStation: OdptStation? = null): Single<List<OdptRailwayFareResponse>>

    /**
     * 運賃情報取得（特定の出発駅・特定の到着駅指定）
     * @param railwayFare 運賃情報ID :required
     * @return 運賃情報リスト
     */
    fun getRailwayFare(railwayFare: OdptRailwayFare): Single<List<OdptRailwayFareResponse>>
}

/**
 * 運賃情報RemoteRepository
 */
class RailwayFareRemoteRepository @Inject constructor(private val odptTrainApiClient: OdptTrainApiClient) : RailwayFareRepository {
    override fun getRailwayFare(operator: OdptOperator): Single<List<OdptRailwayFareResponse>> {
        return odptTrainApiClient.getRailwayFare(operator = operator.id)
    }

    override fun getRailwayFare(fromStation: OdptStation, toStation: OdptStation?): Single<List<OdptRailwayFareResponse>> {
        return odptTrainApiClient.getRailwayFare(fromStation = fromStation.id, toStation = toStation?.id)
    }

    override fun getRailwayFare(railwayFare: OdptRailwayFare): Single<List<OdptRailwayFareResponse>> {
        return odptTrainApiClient.getRailwayFare(sameAs = railwayFare.id)
    }
}