package me.kwsk.odptlibrary.core.api.train

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * 駅別乗降人員情報のテスト
 * Created by teracy on 2017/12/11.
 */
@RunWith(Enclosed::class)
class OdptPassengerSurveyResponseTest {
    @RunWith(AndroidJUnit4::class)
    class デシリアライズのテスト : BaseTest() {
        private fun readTestData(fileName: String): List<OdptPassengerSurveyResponse> {
            val gson = getGson()
            val json = readJsonFile(fileName)
            val listType = object : TypeToken<List<OdptPassengerSurveyResponse>>() {

            }.type

            return gson.fromJson(json, listType)
        }

        @Test
        fun 京王電鉄_京王線() {
            val list: List<OdptPassengerSurveyResponse> = readTestData("PassengerSurvey_Keio_Keio.json")
            Assert.assertEquals(66, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000030E665F", item0.odptId)

            // "2016-06-24T15:13:07+09:00"
            Assert.assertEquals(2016, item0.date.year)
            Assert.assertEquals(6, item0.date.month.value)
            Assert.assertEquals(24, item0.date.dayOfMonth)
            Assert.assertEquals(15, item0.date.hour)
            Assert.assertEquals(13, item0.date.minute)
            Assert.assertEquals(7, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertNull(item0.title)
            Assert.assertEquals("odpt.Operator:Keio", item0.operator?.id)
            Assert.assertEquals(20138, item0.passengerJourneys)

            Assert.assertEquals(1, item0.railwayIdList.size)
            val railway0 = item0.railwayIdList[0]
            Assert.assertEquals("odpt.Railway:Keio.Keio", railway0.id)

            Assert.assertEquals(1, item0.stationIdList.size)
            val station0 = item0.stationIdList[0]
            Assert.assertEquals("odpt.Station:Keio.Keio.Daitabashi", station0.id)

            Assert.assertEquals(2013, item0.surveyYear)
            Assert.assertEquals("odpt.PassengerSurvey:Keio.Daitabashi.2013", item0.sameAs?.id)
        }

        @Test
        fun 京成電鉄_本線() {
            val list: List<OdptPassengerSurveyResponse> = readTestData("PassengerSurvey_Keisei_Main.json")
            Assert.assertEquals(42, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000030E6847", item0.odptId)

            // "2016-06-24T15:12:40+09:00"
            Assert.assertEquals(2016, item0.date.year)
            Assert.assertEquals(6, item0.date.month.value)
            Assert.assertEquals(24, item0.date.dayOfMonth)
            Assert.assertEquals(15, item0.date.hour)
            Assert.assertEquals(12, item0.date.minute)
            Assert.assertEquals(40, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertNull(item0.title)
            Assert.assertEquals("odpt.Operator:Keisei", item0.operator?.id)
            Assert.assertEquals(405, item0.passengerJourneys)

            Assert.assertEquals(1, item0.railwayIdList.size)
            val railway0 = item0.railwayIdList[0]
            Assert.assertEquals("odpt.Railway:Keisei.Main", railway0.id)

            Assert.assertEquals(1, item0.stationIdList.size)
            val station0 = item0.stationIdList[0]
            Assert.assertEquals("odpt.Station:Keisei.Main.Osakura", station0.id)

            Assert.assertEquals(2015, item0.surveyYear)
            Assert.assertEquals("odpt.PassengerSurvey:Keisei.Osakura.2015", item0.sameAs?.id)
        }

        @Test
        fun 都営_三田線() {
            val list: List<OdptPassengerSurveyResponse> = readTestData("PassengerSurvey_Toei_Mita.json")
            Assert.assertEquals(30, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000030E6731", item0.odptId)

            // "2016-06-24T15:10:18+09:00"
            Assert.assertEquals(2016, item0.date.year)
            Assert.assertEquals(6, item0.date.month.value)
            Assert.assertEquals(24, item0.date.dayOfMonth)
            Assert.assertEquals(15, item0.date.hour)
            Assert.assertEquals(10, item0.date.minute)
            Assert.assertEquals(18, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertNull(item0.title)
            Assert.assertEquals("odpt.Operator:Toei", item0.operator?.id)
            Assert.assertEquals(97251, item0.passengerJourneys)

            Assert.assertEquals(2, item0.railwayIdList.size)
            val railway0 = item0.railwayIdList[0]
            Assert.assertEquals("odpt.Railway:Toei.Asakusa", railway0.id)
            val railway1 = item0.railwayIdList[1]
            Assert.assertEquals("odpt.Railway:Toei.Mita", railway1.id)

            Assert.assertEquals(2, item0.stationIdList.size)
            val station0 = item0.stationIdList[0]
            Assert.assertEquals("odpt.Station:Toei.Asakusa.Mita", station0.id)
            val station1 = item0.stationIdList[1]
            Assert.assertEquals("odpt.Station:Toei.Mita.Mita", station1.id)

            Assert.assertEquals(2014, item0.surveyYear)
            Assert.assertEquals("odpt.PassengerSurvey:Toei.Mita.2014", item0.sameAs?.id)
        }

        @Test
        fun 東急_東横線() {
            val list: List<OdptPassengerSurveyResponse> = readTestData("PassengerSurvey_Tokyu_Toyoko.json")
            Assert.assertEquals(293, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000030E6637", item0.odptId)

            // "2016-06-24T15:09:57+09:00"
            Assert.assertEquals(2016, item0.date.year)
            Assert.assertEquals(6, item0.date.month.value)
            Assert.assertEquals(24, item0.date.dayOfMonth)
            Assert.assertEquals(15, item0.date.hour)
            Assert.assertEquals(9, item0.date.minute)
            Assert.assertEquals(57, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertNull(item0.title)
            Assert.assertEquals("odpt.Operator:Tokyu", item0.operator?.id)
            Assert.assertEquals(99302, item0.passengerJourneys)

            Assert.assertEquals(1, item0.railwayIdList.size)
            val railway0 = item0.railwayIdList[0]
            Assert.assertEquals("odpt.Railway:Tokyu.Toyoko", railway0.id)

            Assert.assertEquals(1, item0.stationIdList.size)
            val station0 = item0.stationIdList[0]
            Assert.assertEquals("odpt.Station:Tokyu.Toyoko.Tsunashima", station0.id)

            Assert.assertEquals(2008, item0.surveyYear)
            Assert.assertEquals("odpt.PassengerSurvey:Tokyu.Tsunashima.2008", item0.sameAs?.id)
        }

        @Test
        fun JR東日本_武蔵野線() {
            val list: List<OdptPassengerSurveyResponse> = readTestData("PassengerSurvey_JR-East_Musashino.json")
            Assert.assertEquals(418, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000030F4A90", item0.odptId)

            // "2017-03-31T14:53:30+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(3, item0.date.month.value)
            Assert.assertEquals(31, item0.date.dayOfMonth)
            Assert.assertEquals(14, item0.date.hour)
            Assert.assertEquals(53, item0.date.minute)
            Assert.assertEquals(30, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("西船橋", item0.title)
            Assert.assertEquals("odpt.Operator:JR-East", item0.operator?.id)
            Assert.assertEquals(134362, item0.passengerJourneys)

            Assert.assertEquals(2, item0.railwayIdList.size)
            Assert.assertEquals("odpt.Railway:JR-East.Sobu", item0.railwayIdList[0].id)
            Assert.assertEquals("odpt.Railway:JR-East.Musashino", item0.railwayIdList[1].id)

            Assert.assertEquals(2, item0.stationIdList.size)
            Assert.assertEquals("odpt.Station:JR-East.Sobu.NishiFunabashi", item0.stationIdList[0].id)
            Assert.assertEquals("odpt.Station:JR-East.Musashino.NishiFunabashi", item0.stationIdList[1].id)

            Assert.assertEquals(2015, item0.surveyYear)
            Assert.assertEquals("odpt.PassengerSurvey:JR-East.NishiFunabashi.2015", item0.sameAs?.id)
        }

        @Test
        fun 東武鉄道_伊勢崎線() {
            // NOTE: 路線情報や駅情報では東武スカイツリーラインに属する駅も、駅別乗降人員情報では伊勢崎線に属することになっているので注意
            val list: List<OdptPassengerSurveyResponse> = readTestData("PassengerSurvey_Tobu_Isesaki.json")
            Assert.assertEquals(54, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000030E6B89", item0.odptId)

            // "2016-06-24T15:11:18+09:00"
            Assert.assertEquals(2016, item0.date.year)
            Assert.assertEquals(6, item0.date.month.value)
            Assert.assertEquals(24, item0.date.dayOfMonth)
            Assert.assertEquals(15, item0.date.hour)
            Assert.assertEquals(11, item0.date.minute)
            Assert.assertEquals(18, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("odpt.Operator:Tobu", item0.operator?.id)
            Assert.assertEquals(53190, item0.passengerJourneys)

            Assert.assertEquals(1, item0.railwayIdList.size)
            Assert.assertEquals("odpt.Railway:Tobu.Isesaki", item0.railwayIdList[0].id)

            Assert.assertEquals(1, item0.stationIdList.size)
            Assert.assertEquals("odpt.Station:Tobu.Isesaki.Asakusa", item0.stationIdList[0].id)

            Assert.assertEquals(2015, item0.surveyYear)
            Assert.assertEquals("odpt.PassengerSurvey:Tobu.Asakusa.2015", item0.sameAs?.id)
        }
    }
}