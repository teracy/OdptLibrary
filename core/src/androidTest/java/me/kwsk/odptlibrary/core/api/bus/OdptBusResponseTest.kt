package me.kwsk.odptlibrary.core.api.bus

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * バス情報のテスト
 * Created by teracy on 2017/12/19.
 */
@RunWith(Enclosed::class)
class OdptBusResponseTest {
    @RunWith(AndroidJUnit4::class)
    class デシリアライズのテスト : BaseTest() {
        private fun readTestData(fileName: String): List<OdptBusResponse> {
            val gson = getGson()
            val json = readJsonFile(fileName)
            val listType = object : TypeToken<List<OdptBusResponse>>() {

            }.type

            return gson.fromJson(json, listType)
        }

        @Test
        fun 都営_秋26() {
            // NOTE: API仕様にはない"odpt:note"という項目が存在する
            // NOTE: API仕様にはない"odpt:busroute"という項目が存在する
            val list: List<OdptBusResponse> = readTestData("Bus_Toei_Aki26.64101.1.json")
            Assert.assertEquals(2, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003100876", item0.odptId)

            // "2017-12-19T13:20:19+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(19, item0.date.dayOfMonth)
            Assert.assertEquals(13, item0.date.hour)
            Assert.assertEquals(20, item0.date.minute)
            Assert.assertEquals(19, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            // "2017-12-19T13:20:34+09:00"
            Assert.assertEquals(2017, item0.valid.year)
            Assert.assertEquals(12, item0.valid.month.value)
            Assert.assertEquals(19, item0.valid.dayOfMonth)
            Assert.assertEquals(13, item0.valid.hour)
            Assert.assertEquals(20, item0.valid.minute)
            Assert.assertEquals(34, item0.valid.second)
            Assert.assertEquals("+09:00", item0.valid.zone.id)

            Assert.assertNull(item0.latitude)
            Assert.assertNull(item0.longitude)
            Assert.assertNull(item0.azimuth)
            Assert.assertEquals("A660", item0.busNumber)
            Assert.assertEquals("odpt.BusroutePattern:Toei.Aki26.64101.1", item0.busRoutePattern?.id)
            Assert.assertNull(item0.doorStatus)
            Assert.assertEquals(15, item0.frequency)
            Assert.assertEquals("odpt.BusstopPole:Toei.Kyuukasaibashi.430.4", item0.fromBusStopPole?.id)

            // "2017-12-19T13:20:04+09:00"
            Assert.assertEquals(2017, item0.fromBusStopPoleTime.year)
            Assert.assertEquals(12, item0.fromBusStopPoleTime.month.value)
            Assert.assertEquals(19, item0.fromBusStopPoleTime.dayOfMonth)
            Assert.assertEquals(13, item0.fromBusStopPoleTime.hour)
            Assert.assertEquals(20, item0.fromBusStopPoleTime.minute)
            Assert.assertEquals(4, item0.fromBusStopPoleTime.second)
            Assert.assertEquals("+09:00", item0.fromBusStopPoleTime.zone.id)

            Assert.assertEquals("odpt.Operator:Toei", item0.operator?.id)
            Assert.assertNull(item0.progress)
            Assert.assertNull(item0.speed)
            Assert.assertEquals("odpt.BusstopPole:Toei.Kasaiekimae.307.4", item0.startingBusStopPole?.id)
            Assert.assertEquals("odpt.BusstopPole:Toei.Akihabaraekimae.27.4", item0.terminalBusStopPole?.id)
            Assert.assertEquals("odpt.BusstopPole:Toei.Higashisunayonchoume.1295.2", item0.toBusStopPole?.id)
            Assert.assertEquals("odpt.Bus:Toei.Aki26.64101.1.A660", item0.sameAs?.id)
        }
    }
}