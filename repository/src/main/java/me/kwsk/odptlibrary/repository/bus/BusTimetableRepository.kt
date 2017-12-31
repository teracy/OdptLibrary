package me.kwsk.odptlibrary.repository.bus

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.OdptBusApiClient
import me.kwsk.odptlibrary.core.api.bus.OdptBusTimetableResponse
import me.kwsk.odptlibrary.core.api.common.OdptBusRoutePattern
import me.kwsk.odptlibrary.core.api.common.OdptBusTimetable
import me.kwsk.odptlibrary.core.api.common.OdptCalendar
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import javax.inject.Inject

/**
 * バス時刻表Repository
 * 頻用するであろう引数の組を使いやすくするためのwrapperなので、これ以外の引数の組を使いたい場合は直接[OdptBusApiClient.getBusTimetable]を呼ぶこと
 */
interface BusTimetableRepository {
    /**
     * バス時刻表取得（バス路線名指定）
     * @param operator バス時刻表を配信する事業者のID :required
     * @param busRouteName バス路線名（e.g. "王４０"） :required
     * @param calendar 日付・曜日情報 :optional
     * @return バス時刻表リスト
     */
    fun getBusTimetable(operator: OdptOperator, busRouteName: String, calendar: OdptCalendar? = null): Single<List<OdptBusTimetableResponse>>

    /**
     * バス時刻表取得（バス路線ID指定）
     * @param busRoutePattern バス路線ID :required
     * @param calendar 日付・曜日情報 :optional
     * @return バス時刻表リスト
     */
    fun getBusTimetable(busRoutePattern: OdptBusRoutePattern, calendar: OdptCalendar? = null): Single<List<OdptBusTimetableResponse>>

    /**
     * バス時刻表取得（バス時刻表ID指定）
     * @param busTimetable バス時刻表ID :required
     * @return バス時刻表リスト
     */
    fun getBusTimetable(busTimetable: OdptBusTimetable): Single<List<OdptBusTimetableResponse>>
}

/**
 * バス時刻表RemoteRepository
 */
class BusTimetableRemoteRepository @Inject constructor(private val odptBusApiClient: OdptBusApiClient) : BusTimetableRepository {
    override fun getBusTimetable(operator: OdptOperator, busRouteName: String, calendar: OdptCalendar?): Single<List<OdptBusTimetableResponse>> {
        return odptBusApiClient.getBusTimetable(operator = operator.id, title = busRouteName, calendar = calendar?.id)
    }

    override fun getBusTimetable(busRoutePattern: OdptBusRoutePattern, calendar: OdptCalendar?): Single<List<OdptBusTimetableResponse>> {
        return odptBusApiClient.getBusTimetable(busRoutePattern = busRoutePattern.id, calendar = calendar?.id)
    }

    override fun getBusTimetable(busTimetable: OdptBusTimetable): Single<List<OdptBusTimetableResponse>> {
        return odptBusApiClient.getBusTimetable(sameAs = busTimetable.id)
    }
}