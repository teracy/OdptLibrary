package me.kwsk.odptlibrary.repository.bus

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.OdptBusApiClient
import me.kwsk.odptlibrary.core.api.bus.OdptBusRoutePatternResponse
import me.kwsk.odptlibrary.core.api.common.OdptBusRoute
import me.kwsk.odptlibrary.core.api.common.OdptBusRoutePattern
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import javax.inject.Inject

/**
 * バス路線情報Repository
 * 頻用するであろう引数の組を使いやすくするためのwrapperなので、これ以外の引数の組を使いたい場合は直接[OdptBusApiClient.getBusRoutePattern]を呼ぶこと
 */
interface BusRoutePatternRepository {
    /**
     * バス路線情報取得（バス停名・事業者ID指定）
     * @param busStopPoleName バス停名（e.g. "秋葉原駅前"） :required
     * @param operator バス路線情報を配信する事業者のID :required
     * @return バス路線情報リスト
     */
    fun getBusRoutePattern(busStopPoleName: String, operator: OdptOperator): Single<List<OdptBusRoutePatternResponse>>

    /**
     * バス路線情報取得（バス停名指定、バス系統ID指定可能：バス系統IDを指定しなければ同名のバス停で定義された全事業者・全バス路線情報が取得できる）
     * @param busStopPoleName バス停名（e.g. "秋葉原駅前"） :required
     * @param busRoute バス系統ID :optional
     * @return バス路線情報リスト
     */
    fun getBusRoutePattern(busStopPoleName: String, busRoute: OdptBusRoute? = null): Single<List<OdptBusRoutePatternResponse>>

    /**
     * バス路線情報取得（バス系統ID指定）
     * @param busRoute バス系統ID :required
     * @return バス路線情報リスト
     */
    fun getBusRoutePattern(busRoute: OdptBusRoute): Single<List<OdptBusRoutePatternResponse>>

    /**
     * バス路線情報取得（バス路線ID指定）
     * @param busRoutePattern バス路線情報ID :required
     * @return バス路線情報リスト
     */
    fun getBusRoutePattern(busRoutePattern: OdptBusRoutePattern): Single<List<OdptBusRoutePatternResponse>>
}

/**
 * バス路線情報RemoteRepository
 */
class BusRoutePatternRemoteRepository @Inject constructor(private val odptBusApiClient: OdptBusApiClient) : BusRoutePatternRepository {
    override fun getBusRoutePattern(busStopPoleName: String, operator: OdptOperator): Single<List<OdptBusRoutePatternResponse>> {
        return odptBusApiClient.getBusRoutePattern(title = busStopPoleName, operator = operator.id)
    }

    override fun getBusRoutePattern(busStopPoleName: String, busRoute: OdptBusRoute?): Single<List<OdptBusRoutePatternResponse>> {
        return odptBusApiClient.getBusRoutePattern(title = busStopPoleName, busroute = busRoute?.id)
    }

    override fun getBusRoutePattern(busRoute: OdptBusRoute): Single<List<OdptBusRoutePatternResponse>> {
        return odptBusApiClient.getBusRoutePattern(busroute = busRoute.id)
    }

    override fun getBusRoutePattern(busRoutePattern: OdptBusRoutePattern): Single<List<OdptBusRoutePatternResponse>> {
        return odptBusApiClient.getBusRoutePattern(sameAs = busRoutePattern.id)
    }
}