package me.kwsk.odptlibrary.repository.bus

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.OdptBusApiClient
import me.kwsk.odptlibrary.core.api.bus.OdptBusStopPoleTimetableResponse
import me.kwsk.odptlibrary.core.api.common.*
import javax.inject.Inject

/**
 * バス停時刻表Repository
 * 頻用するであろう引数の組を使いやすくするためのwrapperなので、これ以外の引数の組を使いたい場合は直接[OdptBusApiClient.getBusStopPoleTimetable]を呼ぶこと
 */
interface BusStopPoleTimetableRepository {
    /**
     * バス停時刻表取得（バス系統ID指定、バス停ID・バス方面ID及び日付・曜日情報指定可能）
     * @param busRoute バス系統ID :required
     * @param busStopPole バス停ID :optional
     * @param busDirection バス方面ID :optional
     * @param calendar 日付・曜日情報
     * @return バス停時刻表リスト
     */
    fun getBusStopPoleTimetable(busRoute: OdptBusRoute, busStopPole: OdptBusStopPole? = null,
                                busDirection: OdptBusDirection? = null, calendar: OdptCalendar? = null): Single<List<OdptBusStopPoleTimetableResponse>>

    /**
     * バス停時刻表取得（バス停ID指定、バス方面ID及び日付・曜日情報指定可能）
     * @param busStopPole バス停ID :required
     * @param busDirection バス方面ID :optional
     * @param calendar 日付・曜日情報
     * @return バス停時刻表リスト
     */
    fun getBusStopPoleTimetable(busStopPole: OdptBusStopPole, busDirection: OdptBusDirection? = null, calendar: OdptCalendar? = null): Single<List<OdptBusStopPoleTimetableResponse>>

    /**
     * バス停時刻表取得（バス停時刻表ID指定）
     * @param busStopPoleTimetable バス停時刻表ID :required
     * @return バス停時刻表リスト
     */
    fun getBusStopPoleTimetable(busStopPoleTimetable: OdptBusStopPoleTimetable): Single<List<OdptBusStopPoleTimetableResponse>>
}

/**
 * バス停時刻表RemoteRepository
 */
class BusStopPoleTimetableRemoteRepository @Inject constructor(private val odptBusApiClient: OdptBusApiClient) : BusStopPoleTimetableRepository {
    override fun getBusStopPoleTimetable(busRoute: OdptBusRoute, busStopPole: OdptBusStopPole?, busDirection: OdptBusDirection?, calendar: OdptCalendar?): Single<List<OdptBusStopPoleTimetableResponse>> {
        return odptBusApiClient.getBusStopPoleTimetable(busRoute = busRoute.id, busStopPole = busStopPole?.id, busDirection = busDirection?.id, calendar = calendar?.id)
    }

    override fun getBusStopPoleTimetable(busStopPole: OdptBusStopPole, busDirection: OdptBusDirection?, calendar: OdptCalendar?): Single<List<OdptBusStopPoleTimetableResponse>> {
        return odptBusApiClient.getBusStopPoleTimetable(busStopPole = busStopPole.id, busDirection = busDirection?.id, calendar = calendar?.id)
    }

    override fun getBusStopPoleTimetable(busStopPoleTimetable: OdptBusStopPoleTimetable): Single<List<OdptBusStopPoleTimetableResponse>> {
        return odptBusApiClient.getBusStopPoleTimetable(sameAs = busStopPoleTimetable.id)
    }
}