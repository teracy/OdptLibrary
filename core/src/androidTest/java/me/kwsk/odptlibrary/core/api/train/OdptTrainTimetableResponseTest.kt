package me.kwsk.odptlibrary.core.api.train

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * 列車時刻表情報のテスト
 * Created by teracy on 2017/12/11.
 */
@RunWith(Enclosed::class)
class OdptTrainTimetableResponseTest {
    @RunWith(Enclosed::class)
    class デシリアライズのテスト {
        abstract class BaseDeserializeTest : BaseTest() {
            fun readTestData(fileName: String): List<OdptTrainTimetableResponse> {
                val gson = getGson()
                val json = readJsonFile(fileName)
                val listType = object : TypeToken<List<OdptTrainTimetableResponse>>() {

                }.type

                return gson.fromJson(json, listType)
            }
        }

        @RunWith(AndroidJUnit4::class)
        class 東京メトロ : BaseDeserializeTest() {
            // NOTE: API仕様にはない"odpt:railDirection"という項目が存在する
            // NOTE: API仕様にはない"odpt:terminalStation"という項目が存在する
            @Test
            fun 東西線_A2062S列車_平日() {
                val list: List<OdptTrainTimetableResponse> = readTestData("TrainTimetable_TokyoMetro_Tozai_A2063S_Weekday.json")
                Assert.assertEquals(1, list.size)
                val item0 = list[0]
                Assert.assertEquals("urn:ucode:_00001C000000000000010000031035F6", item0.odptId)

                // "2017-02-18T10:03:17+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(2, item0.date.month.value)
                Assert.assertEquals(18, item0.date.dayOfMonth)
                Assert.assertEquals(10, item0.date.hour)
                Assert.assertEquals(3, item0.date.minute)
                Assert.assertEquals(17, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                Assert.assertEquals("odpt.Calendar:Weekday", item0.calendar?.id)
                Assert.assertFalse(item0.needExtraFee)
                Assert.assertEquals("odpt.Operator:TokyoMetro", item0.operator?.id)
                Assert.assertEquals("odpt.Railway:TokyoMetro.Tozai", item0.railway?.id)
                Assert.assertEquals("odpt.Train:TokyoMetro.Tozai.A2063S", item0.train?.id)
                Assert.assertNull(item0.trainName)
                Assert.assertEquals("A2063S", item0.trainNumber)
                Assert.assertEquals("odpt.TrainOwner:TokyoMetro", item0.trainOwner?.id)
                Assert.assertEquals("odpt.TrainType:TokyoMetro.Local", item0.trainType?.id)
                Assert.assertEquals("odpt.TrainTimetable:TokyoMetro.Tozai.A2063S.Weekdays.Weekday", item0.sameAs?.id)

                Assert.assertNotNull(item0.trainTimetableObjectList)
                Assert.assertEquals(23, item0.trainTimetableObjectList.size)

                // region 始発駅
                val object0 = item0.trainTimetableObjectList[0]
                Assert.assertNull(object0.arrivalStation)
                Assert.assertNull(object0.arrivalTime)
                Assert.assertEquals("odpt.Station:TokyoMetro.Tozai.Nakano", object0.departureStation?.id)

                // "20:06"
                Assert.assertEquals(20, object0.departureTime?.hour)
                Assert.assertEquals(6, object0.departureTime?.minute)

                Assert.assertNull(object0.platformName)
                Assert.assertNull(object0.platformNumber)
                // endregion

                // region 終着駅
                val object22 = item0.trainTimetableObjectList[22]
                Assert.assertEquals("odpt.Station:TokyoMetro.Tozai.NishiFunabashi", object22.arrivalStation?.id)

                // "21:00"
                Assert.assertEquals(21, object22.arrivalTime?.hour)
                Assert.assertEquals(0, object22.arrivalTime?.minute)

                Assert.assertNull(object22.departureStation)
                Assert.assertNull(object22.departureTime)
                Assert.assertNull(object22.platformName)
                Assert.assertNull(object22.platformNumber)
                // endregion
            }
        }

        @RunWith(AndroidJUnit4::class)
        class JR東日本 : BaseDeserializeTest() {
            // NOTE: API仕様にはない"odpt:railDirection"という項目が存在する
            // NOTE: Odpt:TrainTimetableObjectに、API仕様にはない"odpt:index"という項目が存在する
            // NOTE: 終着駅なのにarrival***があったりなかったりするデータがある（路線絞り込み外して列車番号だけにしてもダメ）
            @Test
            fun 常磐緩行線_1247E列車_平日_終着駅と判断できる駅データがない() {
                val list: List<OdptTrainTimetableResponse> = readTestData("TrainTimetable_JR-East_JobanLocal_1247E_Weekday.json")
                Assert.assertEquals(1, list.size)
                val item0 = list[0]
                Assert.assertEquals("urn:ucode:_00001C00000000000001000003107EA8", item0.odptId)

                // "2017-12-07T23:43:47+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(7, item0.date.dayOfMonth)
                Assert.assertEquals(23, item0.date.hour)
                Assert.assertEquals(43, item0.date.minute)
                Assert.assertEquals(47, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                Assert.assertEquals("odpt.Calendar:Weekday", item0.calendar?.id)
                Assert.assertFalse(item0.needExtraFee)
                Assert.assertEquals("odpt.Operator:JR-East", item0.operator?.id)
                Assert.assertEquals("odpt.Railway:JR-East.JobanLocal", item0.railway?.id)
                Assert.assertNull(item0.train)
                Assert.assertNull(item0.trainName)
                Assert.assertEquals("1247E", item0.trainNumber)
                Assert.assertNull(item0.trainOwner)
                Assert.assertEquals("odpt.TrainType:JR-East:Local", item0.trainType?.id)
                Assert.assertEquals("odpt.TrainTimetable:JR-East.JobanLocal.1247E.Weekday", item0.sameAs?.id)

                Assert.assertNotNull(item0.trainTimetableObjectList)
                Assert.assertEquals(12, item0.trainTimetableObjectList.size)

                // region 始発駅
                val object0 = item0.trainTimetableObjectList[0]
                Assert.assertNull(object0.arrivalStation)
                Assert.assertNull(object0.arrivalTime)
                Assert.assertEquals("odpt.Station:JR-East.JobanLocal.Ayase", object0.departureStation?.id)

                // "14:01"
                Assert.assertEquals(14, object0.departureTime?.hour)
                Assert.assertEquals(1, object0.departureTime?.minute)

                Assert.assertNull(object0.platformName)
                Assert.assertNull(object0.platformNumber)
                // endregion

                // region 終着駅
                val object11 = item0.trainTimetableObjectList[11]
                Assert.assertNull(object11.arrivalStation)
                Assert.assertNull(object11.arrivalTime)

                Assert.assertEquals("odpt.Station:JR-East.JobanLocal.Abiko", object11.departureStation?.id)

                // "14:30"
                Assert.assertEquals(14, object11.departureTime?.hour)
                Assert.assertEquals(30, object11.departureTime?.minute)

                Assert.assertNull(object11.platformName)
                Assert.assertNull(object11.platformNumber)
                // endregion
            }

            @Test
            fun 中央快速線_1306T列車_平日_終着駅と判断できる駅データがある() {
                val list: List<OdptTrainTimetableResponse> = readTestData("TrainTimetable_JR-East_ChuoRapid_1306T_Weekday.json")
                Assert.assertEquals(1, list.size)
                val item0 = list[0]
                Assert.assertEquals("urn:ucode:_00001C000000000000010000031096B6", item0.odptId)

                // "2017-12-07T23:43:49+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(7, item0.date.dayOfMonth)
                Assert.assertEquals(23, item0.date.hour)
                Assert.assertEquals(43, item0.date.minute)
                Assert.assertEquals(49, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                Assert.assertEquals("odpt.Calendar:Weekday", item0.calendar?.id)
                Assert.assertFalse(item0.needExtraFee)
                Assert.assertEquals("odpt.Operator:JR-East", item0.operator?.id)
                Assert.assertEquals("odpt.Railway:JR-East.ChuoRapid", item0.railway?.id)
                Assert.assertNull(item0.train)
                Assert.assertNull(item0.trainName)
                Assert.assertEquals("1306T", item0.trainNumber)
                Assert.assertNull(item0.trainOwner)
                Assert.assertEquals("odpt.TrainType:JR-East:ChuoLimitedRapid", item0.trainType?.id)
                Assert.assertEquals("odpt.TrainTimetable:JR-East.ChuoRapid.1306T.Weekday", item0.sameAs?.id)

                Assert.assertNotNull(item0.trainTimetableObjectList)
                Assert.assertEquals(14, item0.trainTimetableObjectList.size)

                // region 始発駅
                val object0 = item0.trainTimetableObjectList[0]
                Assert.assertNull(object0.arrivalStation)
                Assert.assertNull(object0.arrivalTime)
                Assert.assertEquals("odpt.Station:JR-East.ChuoRapid.Takao", object0.departureStation?.id)

                // "13:30"
                Assert.assertEquals(13, object0.departureTime?.hour)
                Assert.assertEquals(30, object0.departureTime?.minute)

                Assert.assertNull(object0.platformName)
                Assert.assertNull(object0.platformNumber)
                // endregion

                // region 終着駅
                val object13 = item0.trainTimetableObjectList[13]
                Assert.assertEquals("odpt.Station:JR-East.ChuoRapid.Tokyo", object13.arrivalStation?.id)
                // "14:28"
                Assert.assertEquals(14, object13.arrivalTime?.hour)
                Assert.assertEquals(28, object13.arrivalTime?.minute)

                Assert.assertNull(object13.departureStation)
                Assert.assertNull(object13.departureTime)

                Assert.assertNull(object13.platformName)
                Assert.assertNull(object13.platformNumber)
                // endregion
            }
        }

        @RunWith(AndroidJUnit4::class)
        class 都営 : BaseDeserializeTest() {
            // NOTE: Odpt:TrainTimetableObjectに、API仕様にはない"odpt:index"という項目が存在する
            @Test
            fun 三田線_535T列車_土休日() {
                val list: List<OdptTrainTimetableResponse> = readTestData("TrainTimetable_Toei_Mita_535T_SaturdayHoliday.json")
                Assert.assertEquals(1, list.size)
                val item0 = list[0]
                Assert.assertEquals("urn:ucode:_00001C0000000000000100000310A981", item0.odptId)

                // "2017-12-08T09:50:50+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(8, item0.date.dayOfMonth)
                Assert.assertEquals(9, item0.date.hour)
                Assert.assertEquals(50, item0.date.minute)
                Assert.assertEquals(50, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                Assert.assertEquals("odpt.Calendar:SaturdayHoliday", item0.calendar?.id)
                Assert.assertFalse(item0.needExtraFee)
                Assert.assertEquals("odpt.Operator:Toei", item0.operator?.id)
                Assert.assertEquals("odpt.Railway:Toei.Mita", item0.railway?.id)
                Assert.assertNull(item0.train)
                Assert.assertNull(item0.trainName)
                Assert.assertEquals("535T", item0.trainNumber)
                Assert.assertNull(item0.trainOwner)
                Assert.assertEquals("odpt.TrainType:Toei.Local", item0.trainType?.id)
                Assert.assertEquals("odpt.TrainTimetable:Toei.Mita.535T.SaturdayHoliday", item0.sameAs?.id)

                Assert.assertNotNull(item0.trainTimetableObjectList)
                Assert.assertEquals(25, item0.trainTimetableObjectList.size)

                // region 始発駅
                val object0 = item0.trainTimetableObjectList[0]
                Assert.assertNull(object0.arrivalStation)
                Assert.assertNull(object0.arrivalTime)
                Assert.assertEquals("odpt.Station:Toei.Mita.ShirokaneTakanawa", object0.departureStation?.id)

                // "05:00"
                Assert.assertEquals(5, object0.departureTime?.hour)
                Assert.assertEquals(0, object0.departureTime?.minute)

                Assert.assertNull(object0.platformName)
                Assert.assertEquals(1, object0.platformNumber)
                // endregion

                // region 終着駅
                val object24 = item0.trainTimetableObjectList[24]
                Assert.assertEquals("odpt.Station:Toei.Mita.NishiTakashimadaira", object24.arrivalStation?.id)

                // "05:46"
                Assert.assertEquals(5, object24.arrivalTime?.hour)
                Assert.assertEquals(46, object24.arrivalTime?.minute)

                Assert.assertNull(object24.departureStation)
                Assert.assertNull(object24.departureTime)
                Assert.assertNull(object24.platformName)
                Assert.assertEquals(1, object0.platformNumber)
                // endregion
            }
        }
    }
}