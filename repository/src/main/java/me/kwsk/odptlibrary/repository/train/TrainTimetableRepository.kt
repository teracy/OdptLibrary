package me.kwsk.odptlibrary.repository.train

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.OdptTrainApiClient
import me.kwsk.odptlibrary.core.api.common.*
import me.kwsk.odptlibrary.core.api.train.OdptTrainTimetableResponse
import javax.inject.Inject

/**
 * 列車時刻表Repository
 */
interface TrainTimetableRepository {
    // NOTE: JR東日本・京王電鉄・都営・東京メトロ・臨海高速鉄道のみ可能
    // FIXME: 京王電鉄は列車時刻表を提供予定であるが、現状まだ実装されていない模様
    // FIXME: 東京臨海高速鉄道は列車時刻表を提供予定であるが、現状まだ実装されていない模様

    /**
     * 列車時刻表取得（路線IDのみ指定、列車IDや列車時刻表IDを利用すると会社・路線・列車番号が一意に決まるため除外している）
     * @param railway 路線ID :required
     * @param calendar 曜日・日付ID :optional
     * @param trainNumber 列車番号 :optional
     * @param trainType 列車種別ID :optional
     * @return 列車時刻表リスト
     */
    fun getTrainTimetable(railway: OdptRailway, calendar: OdptCalendar? = null, trainNumber: String? = null,
                          trainType: OdptTrainType? = null): Single<List<OdptTrainTimetableResponse>>

    /**
     * 列車時刻表取得（列車ID指定：会社・路線・列車番号が一意に決まる）
     * @param train 列車ID :required
     * @return 列車時刻表リスト
     */
    fun getTrainTimetable(train: OdptTrain): Single<List<OdptTrainTimetableResponse>>

    /**
     * 列車時刻表取得（列車時刻表ID指定：会社・路線・列車番号が一意に決まる）
     * @param trainTimetable 列車時刻表ID :required
     * @return 列車時刻表リスト
     */
    fun getTrainTimetable(trainTimetable: OdptTrainTimetable): Single<List<OdptTrainTimetableResponse>>
}

/**
 * 列車時刻表RemoteRepository
 */
class TrainTimetableRemoteRepository @Inject constructor(private val odptTrainApiClient: OdptTrainApiClient) : TrainTimetableRepository {
    override fun getTrainTimetable(railway: OdptRailway, calendar: OdptCalendar?, trainNumber: String?,
                                   trainType: OdptTrainType?): Single<List<OdptTrainTimetableResponse>> {
        return odptTrainApiClient.getTrainTimetable(calendar = calendar?.id, railway = railway.id, trainNumber = trainNumber, trainType = trainType?.id)
    }

    override fun getTrainTimetable(train: OdptTrain): Single<List<OdptTrainTimetableResponse>> {
        return odptTrainApiClient.getTrainTimetable(train = train.id)
    }

    override fun getTrainTimetable(trainTimetable: OdptTrainTimetable): Single<List<OdptTrainTimetableResponse>> {
        return odptTrainApiClient.getTrainTimetable(sameAs = trainTimetable.id)
    }
}