package me.kwsk.odptlibrary.core.api.bus

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * バス停標柱時刻表情報のテスト
 * Created by teracy on 2017/12/19.
 */
@RunWith(Enclosed::class)
class OdptBusStopPoleTimetableResponseTest {
    @RunWith(AndroidJUnit4::class)
    class デシリアライズのテスト : BaseTest() {
        private fun readTestData(fileName: String): List<OdptBusStopPoleTimetableResponse> {
            val gson = getGson()
            val json = readJsonFile(fileName)
            val listType = object : TypeToken<List<OdptBusStopPoleTimetableResponse>>() {

            }.type

            return gson.fromJson(json, listType)
        }

        @Test
        fun 都営_直行01() {
            // NOTE: API仕様にはない"odpt:kana"という項目が存在する
            // NOTE: API仕様にはない"odpt:note"という項目が存在する
            // NOTE: API仕様にはない"odpt:busLocationURL"という項目が存在する
            val list: List<OdptBusStopPoleTimetableResponse> = readTestData("BusStopPoleTimetable_Toei_Chokkou01.json")
            Assert.assertEquals(14, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000031546F3", item0.odptId)

            // "2017-12-07T12:18:44+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(7, item0.date.dayOfMonth)
            Assert.assertEquals(12, item0.date.hour)
            Assert.assertEquals(18, item0.date.minute)
            Assert.assertEquals(44, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("直行０１:品川総合福祉センター前:大井町駅東口行:平日", item0.title)
            Assert.assertNull(item0.valid)
            Assert.assertEquals("odpt.BusDirection:Toei.Ooimachiekihigashiguchi", item0.busDirection?.id)
            Assert.assertEquals("odpt.Busroute:Toei.Chokkou01", item0.busRoute?.id)
            Assert.assertEquals("odpt.BusstopPole:Toei.Shinagawasougoufukushisenta-mae.2011.1", item0.busStopPole?.id)

            Assert.assertEquals("odpt.Calendar:Weekday", item0.calendar?.id)
            Assert.assertEquals("odpt.Operator:Toei", item0.operator?.id)
            Assert.assertEquals("odpt.BusstopPoleTimetable:Toei.Chokkou01.Shinagawasougoufukushisenta-mae.2011.1.Ooimachiekihigashiguchi.Weekday", item0.sameAs?.id)

            Assert.assertEquals(11, item0.busStopPoleTimetableObjectList.size)
            val timetableObject0 = item0.busStopPoleTimetableObjectList[0]
            Assert.assertNull(timetableObject0.arrivalTime)
            Assert.assertNull(timetableObject0.busRoutePattern)
            Assert.assertNull(timetableObject0.canGetOff)
            Assert.assertNull(timetableObject0.canGetOn)

            // "07:27"
            Assert.assertEquals(7, timetableObject0.departureTime?.hour)
            Assert.assertEquals(27, timetableObject0.departureTime?.minute)

            Assert.assertEquals("odpt.BusstopPole:Toei.Ooimachiekihigashiguchi.223.2", timetableObject0.destinationBusStopPole?.id)
            Assert.assertEquals("大井町駅東口行", timetableObject0.destinationSign)
            Assert.assertFalse(timetableObject0.isMidnight!!)
            Assert.assertTrue(timetableObject0.isNonStepBus!!)
            Assert.assertNull(timetableObject0.note)
        }
    }
}