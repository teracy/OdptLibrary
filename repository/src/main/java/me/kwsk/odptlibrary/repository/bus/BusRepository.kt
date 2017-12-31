package me.kwsk.odptlibrary.repository.bus

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.OdptBusApiClient
import me.kwsk.odptlibrary.core.api.bus.OdptBusResponse
import me.kwsk.odptlibrary.core.api.common.OdptBus
import me.kwsk.odptlibrary.core.api.common.OdptBusRoutePattern
import me.kwsk.odptlibrary.core.api.common.OdptBusStopPole
import javax.inject.Inject

/**
 * バス情報Repository
 * バスのロケーションを取得する
 * 頻用するであろう引数の組を使いやすくするためのwrapperなので、これ以外の引数の組を使いたい場合は直接[OdptBusApiClient.getBus]を呼ぶこと
 */
interface BusRepository {
    /**
     * バス情報取得（バス路線ID指定）
     * @param busRoutePattern バス路線ID :required
     * @return バス情報リスト
     */
    fun getBus(busRoutePattern: OdptBusRoutePattern): Single<List<OdptBusResponse>>

    /**
     * バス情報取得（バスが向かっているバス停のID指定、バスが出発したバス停のID指定可能）
     * @param fromBusStopPole バスが出発したバス停のID :optional
     * @param toBusStopPole バスが向かっているバス停のID :required
     * @return バス情報リスト
     */
    fun getBus(fromBusStopPole: OdptBusStopPole? = null, toBusStopPole: OdptBusStopPole): Single<List<OdptBusResponse>>

    /**
     * バス情報取得（バスID指定）
     * @param bus バスID :required
     * @return バス情報リスト
     */
    fun getBus(bus: OdptBus): Single<List<OdptBusResponse>>
}

/**
 * バス情報RemoteRepository
 */
class BusRemoteRepository @Inject constructor(private val odptBusApiClient: OdptBusApiClient) : BusRepository {
    override fun getBus(busRoutePattern: OdptBusRoutePattern): Single<List<OdptBusResponse>> {
        return odptBusApiClient.getBus(busRoutePattern = busRoutePattern.id)
    }

    override fun getBus(fromBusStopPole: OdptBusStopPole?, toBusStopPole: OdptBusStopPole): Single<List<OdptBusResponse>> {
        return odptBusApiClient.getBus(fromBusStopPole = fromBusStopPole?.id, toBusStopPole = toBusStopPole.id)
    }

    override fun getBus(bus: OdptBus): Single<List<OdptBusResponse>> {
        return odptBusApiClient.getBus(sameAs = bus.id)
    }
}