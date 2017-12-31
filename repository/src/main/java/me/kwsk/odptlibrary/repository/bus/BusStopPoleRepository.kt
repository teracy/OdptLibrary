package me.kwsk.odptlibrary.repository.bus

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.OdptBusApiClient
import me.kwsk.odptlibrary.core.api.bus.OdptBusStopPoleResponse
import me.kwsk.odptlibrary.core.api.common.OdptBusRoutePattern
import me.kwsk.odptlibrary.core.api.common.OdptBusStopPole
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import javax.inject.Inject

/**
 * バス停情報Repository
 * 頻用するであろう引数の組を使いやすくするためのwrapperなので、これ以外の引数の組を使いたい場合は直接[OdptBusApiClient.getBusStopPole]を呼ぶこと
 */
interface BusStopPoleRepository {
    /**
     * バス停情報取得（バス停名・事業者ID指定）
     * @param busStopPoleName バス停名（e.g. "秋葉原駅前"） :required
     * @param operator バス停情報を配信する事業者のID :required
     * @return バス停情報リスト
     */
    fun getBusStopPole(busStopPoleName: String, operator: OdptOperator): Single<List<OdptBusStopPoleResponse>>

    /**
     * バス停情報取得（バス停名指定、バス路線ID指定可能：バス路線IDを指定しなければ同名のバス停で定義された全事業者・全路線のバス停情報が取得できる）
     * @param busStopPoleName バス停名（e.g. "秋葉原駅前"） :required
     * @param busRoutePattern バス路線ID :optional
     * @return バス停情報リスト
     */
    fun getBusStopPole(busStopPoleName: String, busRoutePattern: OdptBusRoutePattern? = null): Single<List<OdptBusStopPoleResponse>>

    /**
     * バス停情報取得（バス路線ID指定、標柱番号指定可能）
     * @param busRoutePattern バス路線ID :required
     * @param busStopPoleNumber 標柱番号 :optional
     * @return バス停情報リスト
     */
    fun getBusStopPole(busRoutePattern: OdptBusRoutePattern, busStopPoleNumber: String? = null): Single<List<OdptBusStopPoleResponse>>

    /**
     * バス停情報取得（バス停ID指定）
     * @param busStopPole バス停ID :required
     * @return バス停情報リスト
     */
    fun getBusStopPole(busStopPole: OdptBusStopPole): Single<List<OdptBusStopPoleResponse>>
}

/**
 * バス停情報RemoteRepository
 */
class BusStopPoleRemoteRepository @Inject constructor(private val odptBusApiClient: OdptBusApiClient) : BusStopPoleRepository {
    override fun getBusStopPole(busStopPoleName: String, operator: OdptOperator): Single<List<OdptBusStopPoleResponse>> {
        return odptBusApiClient.getBusStopPole(title = busStopPoleName, operator = operator.id)
    }

    override fun getBusStopPole(busStopPoleName: String, busRoutePattern: OdptBusRoutePattern?): Single<List<OdptBusStopPoleResponse>> {
        return odptBusApiClient.getBusStopPole(title = busStopPoleName, busRoutePattern = busRoutePattern?.id)
    }

    override fun getBusStopPole(busRoutePattern: OdptBusRoutePattern, busStopPoleNumber: String?): Single<List<OdptBusStopPoleResponse>> {
        return odptBusApiClient.getBusStopPole(busRoutePattern = busRoutePattern.id, busStopPoleNumber = busStopPoleNumber)
    }

    override fun getBusStopPole(busStopPole: OdptBusStopPole): Single<List<OdptBusStopPoleResponse>> {
        return odptBusApiClient.getBusStopPole(sameAs = busStopPole.id)
    }
}