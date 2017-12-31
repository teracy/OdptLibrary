package me.kwsk.odptlibrary.core.api.bus

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * バス運賃情報のテスト
 * Created by teracy on 2017/12/19.
 */
@RunWith(Enclosed::class)
class OdptBusRoutePatternFareResponseTest {
    @RunWith(AndroidJUnit4::class)
    class デシリアライズのテスト : BaseTest() {
        private fun readTestData(fileName: String): List<OdptBusRoutePatternFareResponse> {
            val gson = getGson()
            val json = readJsonFile(fileName)
            val listType = object : TypeToken<List<OdptBusRoutePatternFareResponse>>() {

            }.type

            return gson.fromJson(json, listType)
        }

        @Test
        fun 都営() {
            // NOTE: API仕様にはない"odpt:note"という項目が存在する
            val list: List<OdptBusRoutePatternFareResponse> = readTestData("BusRoutePatternFare_Toei.json")
            Assert.assertEquals(1000, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C0000000000000100000311BF2D", item0.odptId)

            // "2017-12-07T12:33:55+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(7, item0.date.dayOfMonth)
            Assert.assertEquals(12, item0.date.hour)
            Assert.assertEquals(33, item0.date.minute)
            Assert.assertEquals(55, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertNull(item0.valid)
            Assert.assertEquals(103, item0.childIcCardFare)
            Assert.assertEquals(110, item0.childTicketFare)
            Assert.assertEquals("odpt.BusroutePattern:Toei.Ou40Kou.40001.1", item0.fromBusroutePattern?.id)
            Assert.assertEquals("odpt.BusstopPole:Toei.Ikebukuroekihigashiguchi.87.6", item0.fromBusStopPole?.id)
            Assert.assertEquals(1, item0.fromBusStopPoleOrder)
            Assert.assertEquals(206, item0.icCardFare)
            Assert.assertEquals("odpt.Operator:Toei", item0.operator?.id)
            Assert.assertEquals(210, item0.ticketFare)
            Assert.assertEquals("odpt.BusroutePattern:Toei.Ou40Kou.40001.1", item0.toBusroutePattern?.id)
            Assert.assertEquals("odpt.BusstopPole:Toei.Ikebukuroekihigashikuchi.2405.10", item0.toBusStopPole?.id)
            Assert.assertEquals(2, item0.toBusStopPoleOrder)
            Assert.assertEquals("odpt.BusroutePatternFare:Toei.Ou40Kou.40001.1.1.Ikebukuroekihigashiguchi.Toei.Ou40Kou.40001.1.2.Ikebukuroekihigashikuchi", item0.sameAs?.id)
        }
    }
}