package me.kwsk.odptlibrary.di.app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import me.kwsk.odptlibrary.App
import me.kwsk.odptlibrary.BuildConfig.API_KEY
import me.kwsk.odptlibrary.core.BuildConfig.API_ROOT
import me.kwsk.odptlibrary.core.api.*
import me.kwsk.odptlibrary.core.api.common.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.LocalTime
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(private var application: App) {
    @Provides
    @Singleton
    fun provideApplication(): App {
        return application
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(level: HttpLoggingInterceptor.Level): Interceptor {
        return HttpLoggingInterceptor()
                .setLevel(level)
    }

    @Provides
    @Singleton
    fun provideOdptTrainApiService(client: OkHttpClient): OdptTrainApiService {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(API_ROOT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .build()
                .create(OdptTrainApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOdptBusApiService(client: OkHttpClient): OdptBusApiService {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(API_ROOT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .build()
                .create(OdptBusApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOdptTrainApiClient(odptService: OdptTrainApiService): OdptTrainApiClient {
        return OdptTrainApiClient(API_KEY, odptService)
    }

    @Provides
    @Singleton
    fun provideOdptBusApiClient(odptService: OdptBusApiService): OdptBusApiClient {
        return OdptBusApiClient(API_KEY, odptService)
    }

    fun createGson(): Gson {
        return GsonBuilder()
                // 共通
                .registerTypeAdapter(ZonedDateTime::class.java, IsoDateTimeDeserializer())
                .registerTypeAdapter(LocalTime::class.java, IsoTimeDeserializer())
                .registerTypeAdapter(OdptCalendar::class.java, OdptCalendarDeserializer())
                .registerTypeAdapter(OdptOperator::class.java, OdptOperatorDeserializer())
                // 列車
                .registerTypeAdapter(OdptPassengerSurvey::class.java, OdptPassengerSurveyDeserializer())
                .registerTypeAdapter(OdptRailDirection::class.java, OdptRailDirectionDeserializer())
                .registerTypeAdapter(OdptRailway::class.java, OdptRailwayDeserializer())
                .registerTypeAdapter(OdptRailwayFare::class.java, OdptRailDirectionDeserializer())
                .registerTypeAdapter(OdptStation::class.java, OdptStationDeserializer())
                .registerTypeAdapter(OdptStationTimetable::class.java, OdptStationTimetableDeserializer())
                .registerTypeAdapter(OdptTicketType::class.java, OdptTicketTypeDeserializer())
                .registerTypeAdapter(OdptTrain::class.java, OdptTrainDeserializer())
                .registerTypeAdapter(OdptTrainOwner::class.java, OdptTrainOwnerDeserializer())
                .registerTypeAdapter(OdptTrainTimetable::class.java, OdptTrainTimetableDeserializer())
                .registerTypeAdapter(OdptTrainType::class.java, OdptTrainTypeDeserializer())
                // バス
                .registerTypeAdapter(OdptBus::class.java, OdptBusDeserializer())
                .registerTypeAdapter(OdptBusDirection::class.java, OdptBusDirectionDeserializer())
                .registerTypeAdapter(OdptBusRoute::class.java, OdptBusRouteDeserializer())
                .registerTypeAdapter(OdptBusStopPoleTimetable::class.java, OdptBusStopPoleTimetableDeserializer())
                .registerTypeAdapter(OdptBusRoutePattern::class.java, OdptBusRoutePatternDeserializer())
                .registerTypeAdapter(OdptBusRoutePatternFare::class.java, OdptBusRoutePatternFareDeserializer())
                .registerTypeAdapter(OdptBusStopPole::class.java, OdptBusStopPoleDeserializer())
                .registerTypeAdapter(OdptBusTimetable::class.java, OdptBusTimetableDeserializer())
                .registerTypeAdapter(OdptOpeningDoor::class.java, OdptOpeningDoorDeserializer())
                .registerTypeAdapter(OdptDoorStatus::class.java, OdptDoorStatusDeserializer())
                .create()
    }

    // FIXME: Sample
    @Provides
    @Singleton
    fun provideSampleText(): String {
        return "injection!!"
    }

    // NOTE: Add dependencies here
}
