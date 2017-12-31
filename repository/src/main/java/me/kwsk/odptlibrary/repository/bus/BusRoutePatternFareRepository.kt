package me.kwsk.odptlibrary.repository.bus

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.OdptBusApiClient
import me.kwsk.odptlibrary.core.api.bus.OdptBusRoutePatternFareResponse
import me.kwsk.odptlibrary.core.api.common.OdptBusRoutePatternFare
import me.kwsk.odptlibrary.core.api.common.OdptBusStopPole
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import javax.inject.Inject

/**
 * バス運賃情報Repository
 * 頻用するであろう引数の組を使いやすくするためのwrapperなので、これ以外の引数の組を使いたい場合は直接[OdptBusApiClient.getBusRoutePatternFare]を呼ぶこと
 */
interface BusRoutePatternFareRepository {
    /**
     * バス運賃情報取得（事業者ID・乗車バス停ID・降車バス停IDすべて指定）
     * @param operator 事業者ID :required
     * @param fromBuStopPole 乗車バス停ID :required
     * @param toBusStopPole 降車バス停ID :required
     * @return バス運賃情報リスト
     */
    fun getBusRoutePatternFare(operator: OdptOperator, fromBuStopPole: OdptBusStopPole, toBusStopPole: OdptBusStopPole): Single<List<OdptBusRoutePatternFareResponse>>

    /**
     * バス運賃情報取得（バス運賃情報ID指定）
     * @param busRoutePatternFare バス運賃情報ID :required
     * @return バス運賃情報リスト
     */
    fun getBusRoutePatternFare(busRoutePatternFare: OdptBusRoutePatternFare): Single<List<OdptBusRoutePatternFareResponse>>
}

/**
 * バス運賃情報RemoteRepository
 */
class BusRoutePatternFareRemoteRepository @Inject constructor(private val odptBusApiClient: OdptBusApiClient) : BusRoutePatternFareRepository {
    override fun getBusRoutePatternFare(operator: OdptOperator, fromBuStopPole: OdptBusStopPole, toBusStopPole: OdptBusStopPole): Single<List<OdptBusRoutePatternFareResponse>> {
        return odptBusApiClient.getBusRoutePatternFare(operator = operator.id, fromBuStopPole = fromBuStopPole.id, toBusStopPole = toBusStopPole.id)
    }

    override fun getBusRoutePatternFare(busRoutePatternFare: OdptBusRoutePatternFare): Single<List<OdptBusRoutePatternFareResponse>> {
        return odptBusApiClient.getBusRoutePatternFare(sameAs = busRoutePatternFare.id)
    }
}