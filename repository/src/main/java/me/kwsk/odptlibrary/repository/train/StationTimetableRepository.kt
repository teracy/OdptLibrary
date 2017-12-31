package me.kwsk.odptlibrary.repository.train

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.OdptTrainApiClient
import me.kwsk.odptlibrary.core.api.common.*
import me.kwsk.odptlibrary.core.api.train.OdptStationTimetableResponse
import javax.inject.Inject

/**
 * 駅時刻表Repository
 */
interface StationTimetableRepository {
    // NOTE: JR東日本・京急電鉄・京王電鉄・西武鉄道・都営・東武鉄道・東京メトロ・臨海高速鉄道のみ可能
    // FIXME: JR東日本は駅時刻表を提供予定であるが、現状まだ実装されていない模様
    // FIXME: 京急電鉄は駅時刻表を提供予定であるが、現状まだ実装されていない模様
    // FIXME: 京王電鉄は駅時刻表を提供予定であるが、現状まだ実装されていない模様
    // FIXME: 西武鉄道は駅時刻表を提供予定であるが、現状まだ実装されていない模様
    // FIXME: 都営は駅時刻表を提供予定であるが、現状まだ実装されていない模様
    // FIXME: 東武は駅時刻表を提供予定であるが、現状まだ実装されていない模様
    // FIXME: 東京臨海高速鉄道は駅時刻表を提供予定であるが、現状まだ実装されていない模様

    /**
     * 駅時刻表取得（路線ID指定、曜日・日付情報ID及び方面ID任意指定可能）
     * @param railway 路線ID :required
     * @param calendar 曜日・日付情報ID :optional
     * @param railDirection 方面ID :optional
     * @return 駅時刻表リスト
     */
    fun getStationTimetable(railway: OdptRailway, calendar: OdptCalendar? = null, railDirection: OdptRailDirection? = null): Single<List<OdptStationTimetableResponse>>

    /**
     * 駅時刻表取得（駅ID指定、曜日・日付情報ID及び方面ID任意指定可能）
     * @param station 駅ID :required
     * @param calendar 運行を行う曜日・日付の日付情報ID :optional
     * @param railDirection 方面ID :optional
     * @return 駅時刻表リスト
     */
    fun getStationTimetable(station: OdptStation, calendar: OdptCalendar? = null, railDirection: OdptRailDirection? = null): Single<List<OdptStationTimetableResponse>>

    /**
     * 駅時刻表取得（駅時刻表ID指定：駅・方面・曜日/日付が一意に定まる）
     * @param stationTimetable 駅時刻表ID :required
     * @return 駅時刻表リスト
     */
    fun getStationTimetable(stationTimetable: OdptStationTimetable): Single<List<OdptStationTimetableResponse>>
}

/**
 * 駅時刻表RemoteRepository
 */
class StationTimetableRemoteRepository @Inject constructor(private val odptTrainApiClient: OdptTrainApiClient) : StationTimetableRepository {
    override fun getStationTimetable(railway: OdptRailway, calendar: OdptCalendar?, railDirection: OdptRailDirection?): Single<List<OdptStationTimetableResponse>> {
        return odptTrainApiClient.getStationTimetable(calendar = calendar?.id, railDirection = railDirection?.id, railway = railway.id)
    }

    override fun getStationTimetable(station: OdptStation, calendar: OdptCalendar?, railDirection: OdptRailDirection?): Single<List<OdptStationTimetableResponse>> {
        return odptTrainApiClient.getStationTimetable(calendar = calendar?.id, railDirection = railDirection?.id, station = station.id)
    }

    override fun getStationTimetable(stationTimetable: OdptStationTimetable): Single<List<OdptStationTimetableResponse>> {
        return odptTrainApiClient.getStationTimetable(sameAs = stationTimetable.id)
    }
}