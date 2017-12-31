package me.kwsk.odptlibrary.core.api.bus

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * バス時刻表情報のテスト
 * Created by teracy on 2017/12/19.
 */
@RunWith(Enclosed::class)
class OdptBusTimetableResponseTest {
    @RunWith(AndroidJUnit4::class)
    class デシリアライズのテスト : BaseTest() {
        private fun readTestData(fileName: String): List<OdptBusTimetableResponse> {
            val gson = getGson()
            val json = readJsonFile(fileName)
            val listType = object : TypeToken<List<OdptBusTimetableResponse>>() {

            }.type

            return gson.fromJson(json, listType)
        }

        @Test
        fun 小田急バス_吉０６() {
            // NOTE: API仕様にはない"odpt:note"という項目が存在する
            // NOTE: "odpt:busTimetableObject"の"odpt:index"が「0」から始まる

            val list: List<OdptBusTimetableResponse> = readTestData("BusTimetable_OdakyuBus_Kichi06.10201.1.json")
            Assert.assertEquals(181, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C0000000000000100000311480F", item0.odptId)

            // "2017-12-13T15:53:01+09:00"
            Assert.assertEquals(2017, item0.date?.year)
            Assert.assertEquals(12, item0.date?.month?.value)
            Assert.assertEquals(13, item0.date?.dayOfMonth)
            Assert.assertEquals(15, item0.date?.hour)
            Assert.assertEquals(53, item0.date?.minute)
            Assert.assertEquals(1, item0.date?.second)
            Assert.assertEquals("+09:00", item0.date?.zone?.id)

            Assert.assertEquals("吉０６", item0.title)

            // "2018-01-15"の23:59:59
            Assert.assertEquals(2018, item0.valid?.year)
            Assert.assertEquals(1, item0.valid?.month?.value)
            Assert.assertEquals(15, item0.valid?.dayOfMonth)
            Assert.assertEquals(23, item0.valid?.hour)
            Assert.assertEquals(59, item0.valid?.minute)
            Assert.assertEquals(59, item0.valid?.second)
            Assert.assertEquals("+09:00", item0.valid?.zone?.id)

            Assert.assertEquals("odpt.BusroutePattern:OdakyuBus.Kichi06.10201.1", item0.busRoutePattern?.id)
            Assert.assertEquals("odpt.Calendar:Specific.OdakyuBus.Kichijouji.Weekday01", item0.calendar?.id)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("odpt.Operator:OdakyuBus", item0.operator?.id)
            Assert.assertEquals("odpt.BusTimetable:OdakyuBus.Kichi06.10201.1.1100.100000143.Specific.OdakyuBus.Kichijouji.Weekday01", item0.sameAs?.id)

            Assert.assertEquals(25, item0.busTimetableObjectList.size)
            val timetableObject0 = item0.busTimetableObjectList[0]

            // "12:02"
            Assert.assertEquals(12, timetableObject0.arrivalTime?.hour)
            Assert.assertEquals(2, timetableObject0.arrivalTime?.minute)

            Assert.assertEquals("odpt.BusstopPole:OdakyuBus.Choufuekikitaguchi.10225.13", timetableObject0.busStopPole?.id)
            Assert.assertFalse(timetableObject0.canGetOff!!)
            Assert.assertTrue(timetableObject0.canGetOn!!)

            // 12:02
            Assert.assertEquals(12, timetableObject0.departureTime?.hour)
            Assert.assertEquals(2, timetableObject0.departureTime?.minute)

            Assert.assertNull(timetableObject0.destinationSign)
            Assert.assertEquals(0, timetableObject0.index)
            Assert.assertFalse(timetableObject0.isMidnight!!)
            Assert.assertNull(timetableObject0.isNonStepBus)
            Assert.assertEquals("調布駅北口:10225:13", timetableObject0.note)
        }
    }
}