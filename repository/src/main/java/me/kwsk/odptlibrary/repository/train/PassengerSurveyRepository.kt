package me.kwsk.odptlibrary.repository.train

import io.reactivex.Single
import me.kwsk.odptlibrary.core.api.OdptTrainApiClient
import me.kwsk.odptlibrary.core.api.code.*
import me.kwsk.odptlibrary.core.api.common.OdptOperator
import me.kwsk.odptlibrary.core.api.common.OdptPassengerSurvey
import me.kwsk.odptlibrary.core.api.common.OdptRailway
import me.kwsk.odptlibrary.core.api.common.OdptStation
import me.kwsk.odptlibrary.core.api.train.OdptPassengerSurveyResponse
import javax.inject.Inject

/**
 * 駅別乗降人員Repository
 */
interface PassengerSurveyRepository {
    // NOTE: 参加している鉄道事業者の内、ゆりかもめを除く全社局で利用可能
    // FIXME: 京急電鉄は駅別乗降人員を提供予定であるが、現状まだ実装されていない模様
    // FIXME: 小田急電鉄は駅別乗降人員を提供予定であるが、現状まだ実装されていない模様
    // FIXME: 西武鉄道は駅別乗降人員を提供予定であるが、現状まだ実装されていない模様
    // FIXME: 東京メトロは駅別乗降人員を取得可能だが、現状では路線IDや駅IDを指定して取得することはできない
    // FIXME: 東京臨海高速鉄道は駅別乗降人員を提供予定であるが、現状まだ実装されていない模様

    /**
     * 駅別乗降人員取得（事業者のみ指定、調査年の個別指定可能）
     * @param operator 事業者ID :required
     * @param surveyYear 調査年（西暦） :optional
     * @return 駅別乗降人員リスト
     */
    fun getPassengerSurvey(operator: OdptOperator, surveyYear: Int? = null): Single<List<OdptPassengerSurveyResponse>>

    /**
     * 駅別乗降人員取得（路線のみ指定、調査年の個別指定可能）
     * @param railway 路線ID :required
     * @param surveyYear 調査年（西暦） :optional
     * @return 駅別乗降人員リスト
     */
    fun getPassengerSurvey(railway: OdptRailway, surveyYear: Int? = null): Single<List<OdptPassengerSurveyResponse>>

    /**
     * 駅別乗降人員取得（駅指定、調査年の個別指定可能）
     * @param station 駅ID :required
     * @param surveyYear 調査年（西暦） :optional
     * @return 駅別乗降人員リスト
     */
    fun getPassengerSurvey(station: OdptStation, surveyYear: Int? = null): Single<List<OdptPassengerSurveyResponse>>

    /**
     * 駅別乗降人員取得（特定の路線・特定の駅・特定の調査年指定）
     * @param passengerSurvey 駅乗降人員情報ID :required
     * @return 駅別乗降人員リスト
     */
    fun getPassengerSurvey(passengerSurvey: OdptPassengerSurvey): Single<List<OdptPassengerSurveyResponse>>
}

/**
 * 駅別乗降人員RemoteRepository
 */
class PassengerSurveyRemoteRepository @Inject constructor(private val odptTrainApiClient: OdptTrainApiClient) : PassengerSurveyRepository {
    override fun getPassengerSurvey(operator: OdptOperator, surveyYear: Int?): Single<List<OdptPassengerSurveyResponse>> {
        return odptTrainApiClient.getPassengerSurvey(operator = operator.id, surveyYear = surveyYear)
    }

    override fun getPassengerSurvey(railway: OdptRailway, surveyYear: Int?): Single<List<OdptPassengerSurveyResponse>> {
        // NOTE: 東武スカイツリーラインの場合、駅別乗降人員APIのみ路線IDが伊勢崎線になっているため読み替えている
        val railwayId: String = if (railway.id == RailwayTobu.TOBU_SKYTREE.id) RailwayTobu.ISESAKI.id else railway.id
        return odptTrainApiClient.getPassengerSurvey(railway = railwayId, surveyYear = surveyYear)
    }

    override fun getPassengerSurvey(station: OdptStation, surveyYear: Int?): Single<List<OdptPassengerSurveyResponse>> {
        // NOTE: 東武スカイツリーラインの場合、駅別乗降人員APIのみ駅IDが伊勢崎線所属になっているため読み替えている
        val stationId = station.id.replace("^odpt\\.Station:Tobu\\.TobuSkytree".toRegex(), "odpt.Station:Tobu.Isesaki")
        return odptTrainApiClient.getPassengerSurvey(station = stationId, surveyYear = surveyYear)
    }

    override fun getPassengerSurvey(passengerSurvey: OdptPassengerSurvey): Single<List<OdptPassengerSurveyResponse>> {
        return odptTrainApiClient.getPassengerSurvey(sameAs = passengerSurvey.id)
    }
}