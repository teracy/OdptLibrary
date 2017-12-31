package me.kwsk.odptlibrary.core.api.bus

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * バス運行路線情報のテスト
 * Created by teracy on 2017/12/19.
 */
@RunWith(Enclosed::class)
class OdptBusRoutePatternResponseTest {
    @RunWith(AndroidJUnit4::class)
    class デシリアライズのテスト : BaseTest() {
        private fun readTestData(fileName: String): List<OdptBusRoutePatternResponse> {
            val gson = getGson()
            val json = readJsonFile(fileName)
            val listType = object : TypeToken<List<OdptBusRoutePatternResponse>>() {

            }.type

            return gson.fromJson(json, listType)
        }

        @Test
        fun 小田急バス() {
            // NOTE: "odpt:busstopPoleOrder"の"odpt:index"が「0」から始まる
            val list: List<OdptBusRoutePatternResponse> = readTestData("BusRoutePattern_OdakyuBus.json")
            Assert.assertEquals(439, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000031541E4", item0.odptId)

            // "2017-12-13T15:52:56+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(13, item0.date.dayOfMonth)
            Assert.assertEquals(15, item0.date.hour)
            Assert.assertEquals(52, item0.date.minute)
            Assert.assertEquals(56, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("吉０４", item0.title)

            // "2018-01-15"の23:59:59
            Assert.assertEquals(2018, item0.valid?.year)
            Assert.assertEquals(1, item0.valid?.month?.value)
            Assert.assertEquals(15, item0.valid?.dayOfMonth)
            Assert.assertEquals(23, item0.valid?.hour)
            Assert.assertEquals(59, item0.valid?.minute)
            Assert.assertEquals(59, item0.valid?.second)
            Assert.assertEquals("+09:00", item0.valid?.zone?.id)

            Assert.assertNull(item0.busLocationURL)
            Assert.assertEquals("odpt.Busroute:OdakyuBus.Kichi04", item0.busRoute?.id)
            Assert.assertEquals("2", item0.direction)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("吉０４:吉祥寺駅～野ヶ谷:10401:2", item0.note)
            Assert.assertEquals("odpt.Operator:OdakyuBus", item0.operator?.id)
            Assert.assertEquals("10401", item0.pattern)
            Assert.assertEquals("odpt.BusroutePattern:OdakyuBus.Kichi04.10401.2", item0.sameAs?.id)
            Assert.assertNull(item0.region)
            Assert.assertEquals(17, item0.busStopPoleOrderList.size)

            val object0 = item0.busStopPoleOrderList[0]
            Assert.assertEquals("odpt.BusstopPole:OdakyuBus.Kichijoujieki.10127.6", object0.busStopPole?.id)
            Assert.assertEquals(0, object0.index)
            Assert.assertEquals("吉祥寺駅:10127:", object0.note)
            Assert.assertNull(object0.openingDoorsToGetOff)
            Assert.assertNull(object0.openingDoorsToGetOn)
        }

        @Test
        fun 都営_東43() {
            val list: List<OdptBusRoutePatternResponse> = readTestData("BusRoutePattern_Toei_Higashi43.json")
            Assert.assertEquals(15, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003153D24", item0.odptId)

            // "2017-12-07T12:33:51+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(7, item0.date.dayOfMonth)
            Assert.assertEquals(12, item0.date.hour)
            Assert.assertEquals(33, item0.date.minute)
            Assert.assertEquals(51, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("東４３", item0.title)
            Assert.assertNull(item0.valid)
            Assert.assertEquals("https://tobus.jp/sp/blsys/stop?routecode=105&ud=1", item0.busLocationURL)
            Assert.assertEquals("odpt.Busroute:Toei.Higashi43", item0.busRoute?.id)
            Assert.assertEquals("1", item0.direction)
            Assert.assertEquals("ひがし、よんじゅうさん", item0.kana)
            Assert.assertEquals("東４３:東４３甲－１:40401:1", item0.note)
            Assert.assertEquals("odpt.Operator:Toei", item0.operator?.id)
            Assert.assertEquals("40401", item0.pattern)
            Assert.assertEquals("odpt.BusroutePattern:Toei.Higashi43.40401.1", item0.sameAs?.id)
            Assert.assertNull(item0.region)
            Assert.assertEquals(35, item0.busStopPoleOrderList.size)

            val object0 = item0.busStopPoleOrderList[0]
            Assert.assertEquals("odpt.BusstopPole:Toei.Arakawadotesoushajomae.66.3", object0.busStopPole?.id)
            Assert.assertEquals(1, object0.index)
            Assert.assertEquals("荒川土手操車所前:66:荒川土手操車所前3", object0.note)
            Assert.assertNull(object0.openingDoorsToGetOff)
            Assert.assertNull(object0.openingDoorsToGetOn)
        }

        @Test
        fun 西武バス_練高01() {
            // NOTE: "odpt:busstopPoleOrder"の"odpt:index"が「0」から始まる
            val list: List<OdptBusRoutePatternResponse> = readTestData("BusRoutePattern_SeibuBus_NeriDaka01.json")
            Assert.assertEquals(2, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003153D66", item0.odptId)

            // "2017-12-04T20:31:28+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(4, item0.date.dayOfMonth)
            Assert.assertEquals(20, item0.date.hour)
            Assert.assertEquals(31, item0.date.minute)
            Assert.assertEquals(28, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("練高０１", item0.title)
            Assert.assertNull(item0.valid)
            Assert.assertNull(item0.busLocationURL)
            Assert.assertEquals("odpt.Busroute:SeibuBus.NeriDaka01", item0.busRoute?.id)
            Assert.assertEquals("1", item0.direction)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("練高０１:練馬高野台駅（光が丘）成増駅南口:1001:1", item0.note)
            Assert.assertEquals("odpt.Operator:SeibuBus", item0.operator?.id)
            Assert.assertEquals("1001", item0.pattern)
            Assert.assertEquals("odpt.BusroutePattern:SeibuBus.NeriDaka01.1001.1", item0.sameAs?.id)
            Assert.assertNull(item0.region)
            Assert.assertEquals(18, item0.busStopPoleOrderList.size)

            val object0 = item0.busStopPoleOrderList[0]
            Assert.assertEquals("odpt.BusstopPole:SeibuBus.Nerimatakanodaieki.20053.1", object0.busStopPole?.id)
            Assert.assertEquals(0, object0.index)
            Assert.assertEquals("練馬高野台駅:20053:練馬高野台駅１", object0.note)
            Assert.assertNull(object0.openingDoorsToGetOff)
            Assert.assertNull(object0.openingDoorsToGetOn)
        }
    }
}