package me.kwsk.odptlibrary.core.api.train

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * 駅時刻表情報のテスト
 * Created by teracy on 2017/12/11.
 */
@RunWith(Enclosed::class)
class OdptStationTimetableResponseTest {
    @RunWith(AndroidJUnit4::class)
    class デシリアライズのテスト : BaseTest() {
        // NOTE: WindowsのChromeでのみの確認で試してないが、小田急電鉄江ノ島線の本鵠沼駅の「鵠」が豆腐になるかも（鵠沼海岸駅の「鵠」は表示できてる）

        private fun readTestData(fileName: String): List<OdptStationTimetableResponse> {
            val gson = getGson()
            val json = readJsonFile(fileName)
            val listType = object : TypeToken<List<OdptStationTimetableResponse>>() {

            }.type

            return gson.fromJson(json, listType)
        }

        @Test
        fun 東京メトロ_半蔵門線_押上() {
            val list: List<OdptStationTimetableResponse> = readTestData("StationTimetable_TokyoMetro_Hanzomon_Oshiage.json")
            Assert.assertEquals(3, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003100F36", item0.odptId)

            // "2017-04-05T14:50:36+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(4, item0.date.month.value)
            Assert.assertEquals(5, item0.date.dayOfMonth)
            Assert.assertEquals(14, item0.date.hour)
            Assert.assertEquals(50, item0.date.minute)
            Assert.assertEquals(36, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("odpt.Calendar:Holiday", item0.calendar?.id)
            Assert.assertEquals("odpt.Operator:TokyoMetro", item0.operator?.id)
            Assert.assertEquals("odpt.RailDirection:TokyoMetro.Shibuya", item0.railDirection?.id)
            Assert.assertNull(item0.railDirectionTitle)
            Assert.assertEquals("odpt.Railway:TokyoMetro.Hanzomon", item0.railway?.id)
            Assert.assertNull(item0.railwayTitle)
            Assert.assertEquals("odpt.Station:TokyoMetro.Hanzomon.Oshiage", item0.station?.id)
            Assert.assertNull(item0.stationTitle)
            Assert.assertEquals("odpt.StationTimetable:TokyoMetro.Hanzomon.Oshiage.Shibuya.Holiday", item0.sameAs?.id)

            Assert.assertNotNull(item0.stationTimetableObjectList)
            Assert.assertEquals(207, item0.stationTimetableObjectList?.size)

            val object0 = item0.stationTimetableObjectList!![0]
            Assert.assertNull(object0.arrivalTime)
            Assert.assertNull(object0.carComposition)

            // "05:06"
            Assert.assertEquals(5, object0.departureTime?.hour)
            Assert.assertEquals(6, object0.departureTime?.minute)

            Assert.assertEquals("odpt.Station:Tokyu.DenEnToshi.ChuoRinkan", object0.destinationStation?.id)
            Assert.assertFalse(object0.isLast)
            Assert.assertTrue(object0.isOrigin)
            Assert.assertNull(object0.note)
            Assert.assertNull(object0.train)
            Assert.assertNull(object0.trainName)
            Assert.assertEquals("odpt.TrainType:TokyoMetro.Local", object0.trainType?.id)
        }
    }
}