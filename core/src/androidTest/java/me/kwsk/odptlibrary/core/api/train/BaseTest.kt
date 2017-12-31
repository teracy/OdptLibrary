package me.kwsk.odptlibrary.core.api.train

import android.support.test.InstrumentationRegistry
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.kwsk.odptlibrary.core.api.IsoTimeDeserializer
import me.kwsk.odptlibrary.core.api.IsoDateTimeDeserializer
import me.kwsk.odptlibrary.core.api.common.*
import org.threeten.bp.LocalTime
import org.threeten.bp.ZonedDateTime
import java.io.BufferedReader

abstract class BaseTest {
    protected fun readJsonFile(fileName: String): String {
        return InstrumentationRegistry.getContext().assets.open(fileName).bufferedReader().use(BufferedReader::readText)
    }

    protected fun getGson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(ZonedDateTime::class.java, IsoDateTimeDeserializer())
                .registerTypeAdapter(LocalTime::class.java, IsoTimeDeserializer())
                .registerTypeAdapter(OdptCalendar::class.java, OdptCalendarDeserializer())
                .registerTypeAdapter(OdptOperator::class.java, OdptOperatorDeserializer())
                .registerTypeAdapter(OdptPassengerSurvey::class.java, OdptPassengerSurveyDeserializer())
                .registerTypeAdapter(OdptRailDirection::class.java, OdptRailDirectionDeserializer())
                .registerTypeAdapter(OdptRailway::class.java, OdptRailwayDeserializer())
                .registerTypeAdapter(OdptRailwayFare::class.java, OdptRailwayFareDeserializer())
                .registerTypeAdapter(OdptStation::class.java, OdptStationDeserializer())
                .registerTypeAdapter(OdptStationTimetable::class.java, OdptStationTimetableDeserializer())
                .registerTypeAdapter(OdptTicketType::class.java, OdptTicketTypeDeserializer())
                .registerTypeAdapter(OdptTrain::class.java, OdptTrainDeserializer())
                .registerTypeAdapter(OdptTrainOwner::class.java, OdptTrainOwnerDeserializer())
                .registerTypeAdapter(OdptTrainTimetable::class.java, OdptTrainTimetableDeserializer())
                .registerTypeAdapter(OdptTrainType::class.java, OdptTrainTypeDeserializer())
                .create()
    }
}