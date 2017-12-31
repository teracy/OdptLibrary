package me.kwsk.odptlibrary.core.api.bus

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * バス停標柱情報のテスト
 * Created by teracy on 2017/12/19.
 */
@RunWith(Enclosed::class)
class OdptBusStopPoleResponseTest {
    @RunWith(AndroidJUnit4::class)
    class デシリアライズのテスト : BaseTest() {
        private fun readTestData(fileName: String): List<OdptBusStopPoleResponse> {
            val gson = getGson()
            val json = readJsonFile(fileName)
            val listType = object : TypeToken<List<OdptBusStopPoleResponse>>() {

            }.type

            return gson.fromJson(json, listType)
        }

        @Test
        fun 都営_秋26() {
            // NOTE: API仕様にはない"odpt:kana"という項目が存在する
            // NOTE: API仕様にはない"odpt:note"という項目が存在する
            // NOTE: API仕様にはない"odpt:busLocationURL"という項目が存在する
            val list: List<OdptBusStopPoleResponse> = readTestData("BusStopPole_Toei_Aki26.64101.1.json")
            Assert.assertEquals(31, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C0000000000000100000315B2DA", item0.odptId)

            // "2017-12-11T16:15:30+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(11, item0.date.dayOfMonth)
            Assert.assertEquals(16, item0.date.hour)
            Assert.assertEquals(15, item0.date.minute)
            Assert.assertEquals(30, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("秋葉原駅前", item0.title)
            Assert.assertNull(item0.valid)
            Assert.assertEquals(35.69891168, item0.latitude)
            Assert.assertEquals(139.7736751, item0.longitude)

            Assert.assertEquals(5, item0.busRoutePatternList?.size)
            Assert.assertEquals("odpt.BusroutePattern:Toei.Aki26.64101.1", item0.busRoutePatternList?.get(0)?.id)

            Assert.assertEquals("4", item0.busStopPoleNumber)

            Assert.assertEquals(3, item0.busStopPoleTimetableList?.size)
            Assert.assertEquals("odpt.BusstopPoleTimetable:Toei.Aki26.Akihabaraekimae.27.4.Kasaiekimae.Holiday", item0.busStopPoleTimetableList?.get(0)?.id)

            Assert.assertEquals(1, item0.operatorList.size)
            Assert.assertEquals("odpt.Operator:Toei", item0.operatorList[0].id)

            Assert.assertEquals("odpt.BusstopPole:Toei.Akihabaraekimae.27.4", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }

        @Test
        fun 小田急バス_吉01() {
            // NOTE: API仕様にはない"odpt:kana"という項目が存在する
            // NOTE: API仕様にはない"odpt:note"という項目が存在する
            val list: List<OdptBusStopPoleResponse> = readTestData("BusStopPole_OdakyuBus_Kichi01.10304.1.json")
            Assert.assertEquals(16, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C0000000000000100000315A9A1", item0.odptId)

            // "2017-12-13T15:52:57+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(13, item0.date.dayOfMonth)
            Assert.assertEquals(15, item0.date.hour)
            Assert.assertEquals(52, item0.date.minute)
            Assert.assertEquals(57, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("文化園前", item0.title)

            // "2018-01-15"の23:59:59
            Assert.assertEquals(2018, item0.valid?.year)
            Assert.assertEquals(1, item0.valid?.month?.value)
            Assert.assertEquals(15, item0.valid?.dayOfMonth)
            Assert.assertEquals(23, item0.valid?.hour)
            Assert.assertEquals(59, item0.valid?.minute)
            Assert.assertEquals(59, item0.valid?.second)
            Assert.assertEquals("+09:00", item0.valid?.zone?.id)

            Assert.assertNull(item0.latitude)
            Assert.assertNull(item0.longitude)

            Assert.assertEquals(33, item0.busRoutePatternList?.size)
            Assert.assertEquals("odpt.BusroutePattern:OdakyuBus.Kichi01.10304.1", item0.busRoutePatternList?.get(0)?.id)

            Assert.assertEquals("1", item0.busStopPoleNumber)

            Assert.assertNull(item0.busStopPoleTimetableList)

            Assert.assertEquals(1, item0.operatorList.size)
            Assert.assertEquals("odpt.Operator:OdakyuBus", item0.operatorList[0].id)

            Assert.assertEquals("odpt.BusstopPole:OdakyuBus.Bunkaemmae.10129.1", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }
    }
}