package me.kwsk.odptlibrary.core.api.airplane

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
                .registerTypeAdapter(OdptAirline::class.java, OdptAirlineDeserializer())
                .registerTypeAdapter(OdptAirport::class.java, OdptAirportDeserializer())
                .registerTypeAdapter(OdptAirportGate::class.java, OdptAirportGateDeserializer())
                .registerTypeAdapter(OdptAirportTerminal::class.java, OdptAirportTerminalDeserializer())
                .registerTypeAdapter(OdptFlightInformation::class.java, OdptFlightInformationDeserializer())
                .registerTypeAdapter(OdptFlightNumber::class.java, OdptFlightNumberDeserializer())
                .registerTypeAdapter(OdptFlightSchedule::class.java, OdptFlightScheduleDeserializer())
                .registerTypeAdapter(OdptFlightStatus::class.java, OdptFlightStatusDeserializer())
                .create()
    }
}