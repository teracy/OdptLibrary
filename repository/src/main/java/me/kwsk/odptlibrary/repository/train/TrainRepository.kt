package me.kwsk.odptlibrary.repository.train

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.OdptTrainApiClient
import me.kwsk.odptlibrary.core.api.common.OdptRailway
import me.kwsk.odptlibrary.core.api.common.OdptTrain
import me.kwsk.odptlibrary.core.api.train.OdptTrainResponse
import javax.inject.Inject

/**
 * 列車情報Repository
 * 列車のロケーションを取得する
 */
interface TrainRepository {
    // NOTE: JR東日本・都営・東京メトロのみ可能
    // FIXME: 都営は列車時刻表を提供予定であるが、現状まだ実装されていない模様

    /**
     * 列車情報取得（路線ID指定）
     * @param railway 路線ID :required
     * @return 列車情報リスト
     */
    fun getTrain(railway: OdptRailway): Single<List<OdptTrainResponse>>

    /**
     * 列車情報取得（列車ID指定）
     * @param train 列車ID :required
     * @return 列車情報リスト（指定したIDの列車が在線していれば1件、していなければ空のリストが返る）
     */
    fun getTrain(train: OdptTrain): Single<List<OdptTrainResponse>>
}

/**
 * 列車情報RemoteRepository
 */
class TrainRemoteRepository @Inject constructor(private val odptTrainApiClient: OdptTrainApiClient) : TrainRepository {
    override fun getTrain(railway: OdptRailway): Single<List<OdptTrainResponse>> {
        return odptTrainApiClient.getTrain(railway = railway.id)
    }

    override fun getTrain(train: OdptTrain): Single<List<OdptTrainResponse>> {
        return odptTrainApiClient.getTrain(sameAs = train.id)
    }
}