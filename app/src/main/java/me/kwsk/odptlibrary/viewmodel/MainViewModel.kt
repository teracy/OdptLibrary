package me.kwsk.odptlibrary.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.kwsk.odptlibrary.core.api.OdptBusApiClient
import me.kwsk.odptlibrary.core.api.common.OdptStation
import me.kwsk.odptlibrary.core.api.code.RailwayOdakyu
import me.kwsk.odptlibrary.di.DaggerUtil
import me.kwsk.odptlibrary.repository.bus.BusRemoteRepository
import me.kwsk.odptlibrary.repository.bus.BusRoutePatternRemoteRepository
import me.kwsk.odptlibrary.repository.bus.BusTimetableRemoteRepository
import me.kwsk.odptlibrary.repository.train.*
import java.util.*
import javax.inject.Inject

class MainViewModel : ViewModel() {
    @Inject
    lateinit var odptBusApiClient: OdptBusApiClient
    @Inject
    lateinit var passengerSurveyRepository: PassengerSurveyRemoteRepository
    @Inject
    lateinit var trainInformationRepository: TrainInformationRemoteRepository
    @Inject
    lateinit var stationRepository: StationRemoteRepository
    @Inject
    lateinit var railwayRepository: RailwayRemoteRepository
    @Inject
    lateinit var railwayFareRepository: RailwayFareRemoteRepository
    @Inject
    lateinit var trainTimetableRepository: TrainTimetableRemoteRepository
    @Inject
    lateinit var trainRepository: TrainRemoteRepository
    @Inject
    lateinit var stationTimetableRepository: StationTimetableRemoteRepository
    @Inject
    lateinit var busRepository: BusRemoteRepository
    @Inject
    lateinit var busRoutePatternRepository: BusRoutePatternRemoteRepository
    @Inject
    lateinit var busTimetableRepository: BusTimetableRemoteRepository

    init {
        DaggerUtil.getComponentBuilder<MainViewModelComponent.Builder>(this)
                .getBuilder(MainViewModelComponent.MainViewModelModule(this))
                .build().injectMembers(this)
    }

    companion object {
        @JvmField
        val TAG: String = MainViewModel::class.java.simpleName
    }

    // TEST
    fun getData() {
//        trainRepository.getTrain(train = OdptTrain("odpt.Train:TokyoMetro.Ginza.B0927"))
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ result ->
//                    Log.d(TAG, String.format(Locale.US, "getTrain(東京メトロ銀座線B0927列車)  data:%d", result.size))
//                }, { throwable: Throwable ->
//                    Log.e(TAG, throwable.message)
//                })
//        trainRepository.getTrain(railway = RailwayTokyoMetro.GINZA.toOdbtRailway())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ result ->
//                    Log.d(TAG, String.format(Locale.US, "getTrain(東京メトロ銀座線)  data:%d", result.size))
//                }, { throwable: Throwable ->
//                    Log.e(TAG, throwable.message)
//                })
//        railwayRepository.getRailway(railway = RailwayTokyoMetro.GINZA.toOdbtRailway())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ result ->
//                    Log.d(TAG, String.format(Locale.US, "getRailway(東京メトロ銀座線)  data:%d", result.size))
//                    Log.d(TAG, String.format(Locale.US, "路線名:%s", result[0].title))
//
//                }, { throwable: Throwable ->
//                    Log.e(TAG, throwable.message)
//                })
//        railwayRepository.getRailway(railway = RailwayTWR.RINKAI.toOdbtRailway())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ result ->
//                    try {
//                        Assert.assertEquals(1, result.size)
//                        Log.d(TAG, String.format(Locale.US, "getRailway(東京臨海高速鉄道りんかい線)  data:%d", result.size))
//                        Log.d(TAG, String.format(Locale.US, "路線名:%s", result[0].title))
//                    } catch (e: AssertionFailedError) {
//                        Log.e(TAG, "\uD83D\uDCA9\uD83D\uDCA9\uD83D\uDCA9東京臨海高速鉄道りんかい線の路線ID直ってない\uD83D\uDCA9\uD83D\uDCA9\uD83D\uDCA9")
//                    }
//
//                }, { throwable: Throwable ->
//                    Log.e(TAG, throwable.message)
//                })
//        railwayRepository.getRailway(railway = RailwayTobu.TOBU_SKYTREE.toOdbtRailway())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ result ->
//                    Log.d(TAG, String.format(Locale.US, "getRailway(東武鉄道東武スカイツリーライン)  data:%d", result.size))
//                    Log.d(TAG, String.format(Locale.US, "路線名:%s", result[0].title))
//
//                }, { throwable: Throwable ->
//                    Log.e(TAG, throwable.message)
//                })
//
//        railwayRepository.getRailway(operator = RailwayOperator.TOKYO_METRO.toOdptOperator())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ result ->
//                    Log.d(TAG, String.format(Locale.US, "getRailway(東京メトロ)  data:%d", result.size))
//                    result.forEach {
//                        Log.d(TAG, String.format(Locale.US, "路線名:%s", it.title))
//                    }
//                }, { throwable: Throwable ->
//                    Log.e(TAG, throwable.message)
//                })
//        railwayRepository.getRailway(operator = RailwayOperator.TOBU.toOdptOperator())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ result ->
//                    Log.d(TAG, String.format(Locale.US, "getRailway(東武鉄道)  data:%d", result.size))
//                    result.forEach {
//                        Log.d(TAG, String.format(Locale.US, "路線名:%s", it.title))
//                    }
//                }, { throwable: Throwable ->
//                    Log.e(TAG, throwable.message)
//                })
//        passengerSurveyRepository.getPassengerSurvey(operator = RailwayOperator.TOBU.toOdptOperator())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ result ->
//                    Log.d(TAG, String.format(Locale.US, "getPassengerSurvey(東武鉄道)  data:%d", result.size))
//                }, { throwable: Throwable ->
//                    Log.e(TAG, throwable.message)
//                })
        railwayRepository.getRailway(railway = RailwayOdakyu.ENOSHIMA.toOdbtRailway())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.d(TAG, String.format(Locale.US, "getRailway(小田急江ノ島線)  data:%d", result.size))
                    result.forEach {
                        Log.d(TAG, String.format(Locale.US, "路線名:%s", it.title))
                        it.stationOrderList.forEach {
                            Log.d(TAG, String.format(Locale.US, "駅名:%s", it.stationTitle))
                        }
                    }
                }, { throwable: Throwable ->
                    Log.e(TAG, throwable.message)
                })

        stationRepository.getStation(station = OdptStation("odpt.Station:Odakyu.Enoshima.HonKugenuma"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.d(TAG, String.format(Locale.US, "getStation(小田急江ノ島線本鵠沼駅)  data:%d", result.size))
                    result.forEach {
                        Log.d(TAG, String.format(Locale.US, "駅名:%s", it.title))
                    }
                }, { throwable: Throwable ->
                    Log.e(TAG, throwable.message)
                })

        stationRepository.getStation(station = OdptStation("odpt.Station:Odakyu.Enoshima.KugenumaKaigan"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.d(TAG, String.format(Locale.US, "getStation(小田急江ノ島線鵠沼海岸駅)  data:%d", result.size))
                    result.forEach {
                        Log.d(TAG, String.format(Locale.US, "駅名:%s", it.title))
                    }
                }, { throwable: Throwable ->
                    Log.e(TAG, throwable.message)
                })
    }
}
