package me.kwsk.odptlibrary.core.api.bus

import android.support.test.InstrumentationRegistry
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.kwsk.odptlibrary.core.api.IsoDateTimeDeserializer
import me.kwsk.odptlibrary.core.api.IsoTimeDeserializer
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
}