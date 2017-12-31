package me.kwsk.odptlibrary.repository.train

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.OdptTrainApiClient
import me.kwsk.odptlibrary.core.api.common.OdptRailway
import me.kwsk.odptlibrary.core.api.common.OdptStation
import me.kwsk.odptlibrary.core.api.train.OdptStationResponse
import javax.inject.Inject

/**
 * 駅情報Repository
 */
interface StationRepository {
    // NOTE: 参加している全鉄道事業者で取得可能
    // FIXME: 京急電鉄は駅情報を提供予定であるが、現状まだ実装されていない模様
    /**
     * 駅情報取得（路線指定、駅名指定可能）
     * @param railway 路線ID :required
     * @param stationName 駅名 :optional
     * @return 駅情報リスト
     */
    fun getStation(railway: OdptRailway, stationName: String? = null): Single<List<OdptStationResponse>>

    /**
     * 駅情報取得（駅ID指定）
     * @param station 駅ID :required
     * @return 駅情報リスト
     */
    fun getStation(station: OdptStation): Single<List<OdptStationResponse>>

    /**
     * 駅情報取得（駅名指定）
     * @param stationName 駅名 :required
     * @return 駅情報リスト：複数路線の複数の駅が返る可能性がある
     */
    fun getStation(stationName: String): Single<List<OdptStationResponse>>
}

/**
 * 駅情報RemoteRepository
 */
class StationRemoteRepository @Inject constructor(private val odptTrainApiClient: OdptTrainApiClient) : StationRepository {
    override fun getStation(railway: OdptRailway, stationName: String?): Single<List<OdptStationResponse>> {
        return odptTrainApiClient.getStation(railway = railway.id, title = stationName)
    }

    override fun getStation(station: OdptStation): Single<List<OdptStationResponse>> {
        return odptTrainApiClient.getStation(sameAs = station.id)
    }

    override fun getStation(stationName: String): Single<List<OdptStationResponse>> {
        return odptTrainApiClient.getStation(title = stationName)
    }
}
