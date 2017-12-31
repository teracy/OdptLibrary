package me.kwsk.odptlibrary.core.api.airplane

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * 空港時刻表のテスト
 * Created by teracy on 2017/12/28.
 */
@RunWith(Enclosed::class)
class OdptFlightScheduleResponseTest {
    @RunWith(AndroidJUnit4::class)
    class デシリアライズのテスト : BaseTest() {
        private fun readTestData(fileName: String): List<OdptFlightScheduleResponse> {
            val gson = getGson()
            val json = readJsonFile(fileName)
            val listType = object : TypeToken<List<OdptFlightScheduleResponse>>() {

            }.type

            return gson.fromJson(json, listType)
        }

        @Test
        fun 全日空() {
            // FIXME: @idはrequiredな項目だが、全日空のデータには存在しない
            val list: List<OdptFlightScheduleResponse> = readTestData("FlightSchedule_ANA.json")
            Assert.assertEquals(7, list.size)
            val item0 = list[0]
            Assert.assertEquals("", item0.odptId)

            // "2017-12-08T14:44:11+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(14, item0.date.hour)
            Assert.assertEquals(44, item0.date.minute)
            Assert.assertEquals(11, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("odpt.Calendar:Thursday", item0.calendar?.id)
            Assert.assertEquals("odpt.Operator:ANA", item0.operator?.id)
            Assert.assertEquals("odpt.FlightSchedule:ANA.Thursday", item0.sameAs?.id)

            Assert.assertEquals(1273, item0.flightScheduleObjectList.size)

            val object0 = item0.flightScheduleObjectList[0]
            Assert.assertEquals("odpt.Airline:ANA", object0.airline?.id)

            // "11:50"
            Assert.assertEquals(11, object0.arrivalTime?.hour)
            Assert.assertEquals(50, object0.arrivalTime?.minute)

            Assert.assertEquals("odpt.Airport:HND", object0.departureAirport?.id)

            // "09:45"
            Assert.assertEquals(9, object0.departureTime?.hour)
            Assert.assertEquals(45, object0.departureTime?.minute)

            Assert.assertEquals("odpt.Airport:FUK", object0.destinationAirport?.id)
            Assert.assertEquals("ANA247", object0.flightNumber)

            // "2017-12-08T14:44:11+09:00"
            Assert.assertEquals(2017, object0.validFrom?.year)
            Assert.assertEquals(12, object0.validFrom?.month?.value)
            Assert.assertEquals(8, object0.validFrom?.dayOfMonth)
            Assert.assertEquals(14, object0.validFrom?.hour)
            Assert.assertEquals(44, object0.validFrom?.minute)
            Assert.assertEquals(11, object0.validFrom?.second)
            Assert.assertEquals("+09:00", object0.validFrom?.zone?.id)
        }
    }
}