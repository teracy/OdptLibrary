package me.kwsk.odptlibrary.core.api.airplane

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * 空港発着情報のテスト
 * Created by teracy on 2017/12/28.
 */
@RunWith(Enclosed::class)
class OdptFlightInformationResponseTest {
    @RunWith(Enclosed::class)
    class デシリアライズのテスト {
        abstract class BaseDeserializeTest : BaseTest() {
            fun readTestData(fileName: String): List<OdptFlightInformationResponse> {
                val gson = getGson()
                val json = readJsonFile(fileName)
                val listType = object : TypeToken<List<OdptFlightInformationResponse>>() {

                }.type

                return gson.fromJson(json, listType)
            }
        }

        @RunWith(AndroidJUnit4::class)
        class 羽田空港 : BaseDeserializeTest() {
            // FIXME: @idはrequiredな項目だが、羽田空港のデータには存在しない
            // NOTE: odpt:flightNumberがない代わりにodpt:flightNumbersというarrayが存在する
            // FIXME: odpt:airlineは存在するが、命名ルール（odpt.Airline:ICAO航空会社コード）に則っていない
            // 仕様書に存在しない"odpt:checkInCounters"というarrayが存在する
            // FIXME: 出発空港IDが誤った仕様の"odpt.departureAirport"で定義されている
            @Test
            fun 到着情報() {
                val list: List<OdptFlightInformationResponse> = readTestData("FlightInformationArrival_HND-TIAT.json")
                Assert.assertEquals(113, list.size)
                val item0 = list[0]
                Assert.assertEquals("", item0.odptId)

                // "2017-12-28T13:00:09+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(28, item0.date.dayOfMonth)
                Assert.assertEquals(13, item0.date.hour)
                Assert.assertEquals(0, item0.date.minute)
                Assert.assertEquals(9, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                Assert.assertEquals("odpt.Airport:TPE", item0.departureAirport?.id)

                Assert.assertNull(item0.actualTime)

                Assert.assertEquals("APJ", item0.airline)
                Assert.assertNull(item0.baggageClaim)
                Assert.assertEquals("odpt.Airport:HND", item0.destinationAirport?.id)

                // "01:34"
                Assert.assertEquals(1, item0.estimatedTime?.hour)
                Assert.assertEquals(34, item0.estimatedTime?.minute)

                // ["MM0858"]
                Assert.assertEquals(1, item0.flightNumber!!.values.size)
                Assert.assertEquals("MM0858", item0.flightNumber!!.values[0])
                Assert.assertEquals("odpt.FlightStatus:Arrived", item0.flightStatus?.id)
                Assert.assertNull(item0.gate)

                // "00:40"
                Assert.assertEquals(0, item0.scheduledTime?.hour)
                Assert.assertEquals(40, item0.scheduledTime?.minute)

                Assert.assertEquals("odpt.AirportTerminal:HND.InternationalPassengerTerminal", item0.terminal?.id)
                Assert.assertEquals("odpt.Operator:HND-TIAT", item0.operator?.id)
                Assert.assertEquals("odpt.FlightInformationArrival:HND.MM0858", item0.sameAs?.id)
            }

            @Test
            fun 出発情報() {
                val list: List<OdptFlightInformationResponse> = readTestData("FlightInformationDeparture_HND-TIAT.json")
                Assert.assertEquals(113, list.size)
                val item0 = list[0]
                Assert.assertEquals("", item0.odptId)

                // "2017-12-28T13:38:32+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(28, item0.date.dayOfMonth)
                Assert.assertEquals(13, item0.date.hour)
                Assert.assertEquals(38, item0.date.minute)
                Assert.assertEquals(32, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                Assert.assertEquals("odpt.Airport:HND", item0.departureAirport?.id)

                // "00:04"
                Assert.assertEquals(0, item0.actualTime?.hour)
                Assert.assertEquals(4, item0.actualTime?.minute)

                Assert.assertEquals("JAL", item0.airline)
                Assert.assertNull(item0.baggageClaim)
                Assert.assertEquals("odpt.Airport:BKK", item0.destinationAirport?.id)

                // "00:04"
                Assert.assertEquals(0, item0.estimatedTime?.hour)
                Assert.assertEquals(4, item0.estimatedTime?.minute)

                // ["JL0033","UL3357","PG4152"]
                Assert.assertEquals(3, item0.flightNumber!!.values.size)
                Assert.assertEquals("JL0033", item0.flightNumber!!.values[0])
                Assert.assertEquals("UL3357", item0.flightNumber!!.values[1])
                Assert.assertEquals("PG4152", item0.flightNumber!!.values[2])
                Assert.assertEquals("odpt.FlightStatus:Takeoff", item0.flightStatus?.id)
                Assert.assertEquals("odpt.AirportGate:HND.InternationalPassengerTerminal.113", item0.gate)

                // "00:05"
                Assert.assertEquals(0, item0.scheduledTime?.hour)
                Assert.assertEquals(5, item0.scheduledTime?.minute)

                Assert.assertEquals("odpt.AirportTerminal:HND.InternationalPassengerTerminal", item0.terminal?.id)
                Assert.assertEquals("odpt.Operator:HND-TIAT", item0.operator?.id)
                Assert.assertEquals("odpt.FlightInformationDeparture:HND.JL0033", item0.sameAs?.id)
            }
        }

        @RunWith(AndroidJUnit4::class)
        class 成田空港 : BaseDeserializeTest() {
            // FIXME: odpt:sameAsは存在するが、命名ルール（odpt.FlightInformationArrival:到着空港のIATA空港コード（3レターコード）.便名（フライトナンバー））に則っていない
            @Test
            fun 到着情報() {
                val list: List<OdptFlightInformationResponse> = readTestData("FlightInformationArrival_NAA.json")
                Assert.assertEquals(805, list.size)
                val item0 = list[0]
                Assert.assertEquals("urn:uuid:fb207a2d-686b-440b-9134-c85a37b03260", item0.odptId)

                // "2017-12-28T14:10:02+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(28, item0.date.dayOfMonth)
                Assert.assertEquals(14, item0.date.hour)
                Assert.assertEquals(10, item0.date.minute)
                Assert.assertEquals(2, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                Assert.assertEquals("odpt.Airport:MEX", item0.departureAirport?.id)

                Assert.assertNull(item0.actualTime)

                Assert.assertEquals("odpt.Airline:AMX", item0.airline)
                Assert.assertNull(item0.baggageClaim)
                Assert.assertEquals("odpt.Airport:NRT", item0.destinationAirport?.id)

                // "06:40"
                Assert.assertEquals(6, item0.estimatedTime?.hour)
                Assert.assertEquals(40, item0.estimatedTime?.minute)

                // "AM0058"
                Assert.assertEquals(1, item0.flightNumber!!.values.size)
                Assert.assertEquals("AM0058", item0.flightNumber!!.values[0])
                Assert.assertEquals("odpt.FlightStatus:Arrived", item0.flightStatus?.id)
                Assert.assertEquals("22", item0.gate)

                // "06:05"
                Assert.assertEquals(6, item0.scheduledTime?.hour)
                Assert.assertEquals(5, item0.scheduledTime?.minute)

                Assert.assertEquals("odpt.AirportTerminal:NRT.Terminal1", item0.terminal?.id)
                Assert.assertEquals("odpt.Operator:NAA", item0.operator?.id)
                Assert.assertEquals("odpt.FlightInformationArrival:NRT.AMX.AM0058", item0.sameAs?.id)
            }

            @Test
            fun 出発情報() {
                val list: List<OdptFlightInformationResponse> = readTestData("FlightInformationDeparture_NAA.json")
                Assert.assertEquals(781, list.size)
                val item0 = list[0]
                Assert.assertEquals("urn:uuid:7677f92e-f047-4630-8a9e-f47c260e5831", item0.odptId)

                // "2017-12-28T14:10:02+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(28, item0.date.dayOfMonth)
                Assert.assertEquals(14, item0.date.hour)
                Assert.assertEquals(10, item0.date.minute)
                Assert.assertEquals(2, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                Assert.assertEquals("odpt.Airport:NRT", item0.departureAirport?.id)

                Assert.assertNull(item0.actualTime)

                Assert.assertEquals("odpt.Airline:JJP", item0.airline)
                Assert.assertNull(item0.baggageClaim)
                Assert.assertEquals("odpt.Airport:FUK", item0.destinationAirport?.id)

                // "06:09"
                Assert.assertEquals(6, item0.estimatedTime?.hour)
                Assert.assertEquals(9, item0.estimatedTime?.minute)

                // "GK0501"
                Assert.assertEquals(1, item0.flightNumber!!.values.size)
                Assert.assertEquals("GK0501", item0.flightNumber!!.values[0])
                Assert.assertEquals("odpt.FlightStatus:Takeoff", item0.flightStatus?.id)
                Assert.assertEquals("164", item0.gate)

                // "06:05"
                Assert.assertEquals(6, item0.scheduledTime?.hour)
                Assert.assertEquals(5, item0.scheduledTime?.minute)

                Assert.assertEquals("odpt.AirportTerminal:NRT.Terminal3", item0.terminal?.id)
                Assert.assertEquals("odpt.Operator:NAA", item0.operator?.id)
                Assert.assertEquals("odpt.FlightInformationDeparture:NRT.JJP.GK0501", item0.sameAs?.id)
            }
        }
    }
}
