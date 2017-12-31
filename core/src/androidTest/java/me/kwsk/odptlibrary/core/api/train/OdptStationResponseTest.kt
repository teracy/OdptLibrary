package me.kwsk.odptlibrary.core.api.train

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * 駅情報
 * Created by teracy on 2017/12/11.
 */
@RunWith(Enclosed::class)
class OdptStationResponseTest {
    // TODO: 2017/12/27 全事業者のデータは揃ったので、以後データに更新があった場合のodptIdとdateのテストについてはnullでないことのみチェックでよい
    @RunWith(AndroidJUnit4::class)
    class デシリアライズのテスト : BaseTest() {
        private fun readTestData(fileName: String): List<OdptStationResponse> {
            val gson = getGson()
            val json = readJsonFile(fileName)
            val listType = object : TypeToken<List<OdptStationResponse>>() {

            }.type

            return gson.fromJson(json, listType)
        }

        @Test
        fun 東京メトロ_銀座線() {
            val list: List<OdptStationResponse> = readTestData("Station_TokyoMetro_Ginza.json")
            Assert.assertEquals(19, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000030C46B5", item0.odptId)

            // "2017-12-08T13:58:05+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(13, item0.date.hour)
            Assert.assertEquals(58, item0.date.minute)
            Assert.assertEquals(5, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("青山一丁目", item0.title)
            Assert.assertEquals(139.724145, item0.longitude)
            Assert.assertEquals(35.67279, item0.latitude)
            Assert.assertNull(item0.connectingRailwayIdList)
            Assert.assertNull(item0.exitIdList)
            Assert.assertNull(item0.facilityIdList)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("odpt.Operator:TokyoMetro", item0.operator?.id)
            Assert.assertNull(item0.passengerSurveyIdList)
            Assert.assertEquals("odpt.Railway:TokyoMetro.Ginza", item0.railway?.id)
            Assert.assertEquals("G04", item0.stationCode)
            Assert.assertNull(item0.stationTimetableIdList)
            Assert.assertEquals("odpt.Station:TokyoMetro.Ginza.AoyamaItchome", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }

        @Test
        fun JR東日本_常磐緩行線() {
            // NOTE: odpt:stationCodeが送信されない路線がある
            val list: List<OdptStationResponse> = readTestData("Station_JR-East_JobanLocal.json")
            Assert.assertEquals(15, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003102720", item0.odptId)

            // "2017-12-08T01:10:57+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(1, item0.date.hour)
            Assert.assertEquals(10, item0.date.minute)
            Assert.assertEquals(57, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("北千住", item0.title)
            Assert.assertEquals(139.80483977015805, item0.longitude)
            Assert.assertEquals(35.7498868685489, item0.latitude)
            Assert.assertNull(item0.connectingRailwayIdList)
            Assert.assertNull(item0.exitIdList)
            Assert.assertNull(item0.facilityIdList)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("odpt.Operator:JR-East", item0.operator?.id)
            Assert.assertNull(item0.passengerSurveyIdList)
            Assert.assertEquals("odpt.Railway:JR-East.JobanLocal", item0.railway?.id)
            Assert.assertNull(item0.stationCode)
            Assert.assertNull(item0.stationTimetableIdList)
            Assert.assertEquals("odpt.Station:JR-East.JobanLocal.KitaSenju", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }

        @Test
        fun 京王電鉄_京王線() {
            val list: List<OdptStationResponse> = readTestData("Station_Keio_Keio.json")
            Assert.assertEquals(32, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C0000000000000100000310213C", item0.odptId)

            // "2017-12-08T13:40:52+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(13, item0.date.hour)
            Assert.assertEquals(40, item0.date.minute)
            Assert.assertEquals(52, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("新宿", item0.title)
            Assert.assertEquals(139.699141, item0.longitude)
            Assert.assertEquals(35.690127, item0.latitude)
            Assert.assertNull(item0.connectingRailwayIdList)
            Assert.assertNull(item0.exitIdList)
            Assert.assertNull(item0.facilityIdList)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("odpt.Operator:Keio", item0.operator?.id)
            Assert.assertNull(item0.passengerSurveyIdList)
            Assert.assertEquals("odpt.Railway:Keio.Keio", item0.railway?.id)
            Assert.assertEquals("KO1", item0.stationCode)
            Assert.assertNull(item0.stationTimetableIdList)
            Assert.assertEquals("odpt.Station:Keio.Keio.Shinjuku", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }

        @Test
        fun 京成電鉄_本線() {
            val list: List<OdptStationResponse> = readTestData("Station_Keisei_Main.json")
            Assert.assertEquals(42, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C0000000000000100000310218D", item0.odptId)

            // "2017-12-08T13:40:52+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(13, item0.date.hour)
            Assert.assertEquals(40, item0.date.minute)
            Assert.assertEquals(52, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("京成上野", item0.title)
            Assert.assertEquals(139.777882, item0.longitude)
            Assert.assertEquals(35.718123, item0.latitude)
            Assert.assertNull(item0.connectingRailwayIdList)
            Assert.assertNull(item0.exitIdList)
            Assert.assertNull(item0.facilityIdList)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("odpt.Operator:Keisei", item0.operator?.id)
            Assert.assertNull(item0.passengerSurveyIdList)
            Assert.assertEquals("odpt.Railway:Keisei.Main", item0.railway?.id)
            Assert.assertEquals("KS01", item0.stationCode)
            Assert.assertNull(item0.stationTimetableIdList)
            Assert.assertEquals("odpt.Station:Keisei.Main.KeiseiUeno", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }

        @Test
        fun 小田急電鉄_小田原線() {
            val list: List<OdptStationResponse> = readTestData("Station_Odakyu_Odawara.json")
            Assert.assertEquals(47, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003102288", item0.odptId)

            // "2017-12-08T13:40:51+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(13, item0.date.hour)
            Assert.assertEquals(40, item0.date.minute)
            Assert.assertEquals(51, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("新宿", item0.title)
            Assert.assertEquals(139.699517, item0.longitude)
            Assert.assertEquals(35.691326, item0.latitude)
            Assert.assertNull(item0.connectingRailwayIdList)
            Assert.assertNull(item0.exitIdList)
            Assert.assertNull(item0.facilityIdList)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("odpt.Operator:Odakyu", item0.operator?.id)
            Assert.assertNull(item0.passengerSurveyIdList)
            Assert.assertEquals("odpt.Railway:Odakyu.Odawara", item0.railway?.id)
            Assert.assertEquals("OH01", item0.stationCode)
            Assert.assertNull(item0.stationTimetableIdList)
            Assert.assertEquals("odpt.Station:Odakyu.Odawara.Shinjuku", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }

        @Test
        fun 西武鉄道_西武池袋線() {
            val list: List<OdptStationResponse> = readTestData("Station_Seibu_Ikebukuro.json")
            Assert.assertEquals(31, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003102AC3", item0.odptId)

            // "2017-12-08T13:40:52+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(13, item0.date.hour)
            Assert.assertEquals(40, item0.date.minute)
            Assert.assertEquals(52, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("池袋", item0.title)
            Assert.assertEquals(139.711744, item0.longitude)
            Assert.assertEquals(35.728572, item0.latitude)
            Assert.assertNull(item0.connectingRailwayIdList)
            Assert.assertNull(item0.exitIdList)
            Assert.assertNull(item0.facilityIdList)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("odpt.Operator:Seibu", item0.operator?.id)
            Assert.assertNull(item0.passengerSurveyIdList)
            Assert.assertEquals("odpt.Railway:Seibu.Ikebukuro", item0.railway?.id)
            Assert.assertEquals("SI01", item0.stationCode)
            Assert.assertNull(item0.stationTimetableIdList)
            Assert.assertEquals("odpt.Station:Seibu.Ikebukuro.Ikebukuro", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }

        @Test
        fun 東武鉄道_東武スカイツリーライン() {
            val list: List<OdptStationResponse> = readTestData("Station_Tobu_TobuSkytree.json")
            Assert.assertEquals(30, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003102BA4", item0.odptId)

            // "2017-12-08T13:40:52+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(13, item0.date.hour)
            Assert.assertEquals(40, item0.date.minute)
            Assert.assertEquals(52, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("浅草", item0.title)
            Assert.assertEquals(139.798474, item0.longitude)
            Assert.assertEquals(35.712039, item0.latitude)
            Assert.assertNull(item0.connectingRailwayIdList)
            Assert.assertNull(item0.exitIdList)
            Assert.assertNull(item0.facilityIdList)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("odpt.Operator:Tobu", item0.operator?.id)
            Assert.assertNull(item0.passengerSurveyIdList)
            Assert.assertEquals("odpt.Railway:Tobu.TobuSkytree", item0.railway?.id)
            Assert.assertEquals("TS-01", item0.stationCode)
            Assert.assertNull(item0.stationTimetableIdList)
            Assert.assertEquals("odpt.Station:Tobu.TobuSkytree.Asakusa", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }

        @Test
        fun 都営_三田線() {
            val list: List<OdptStationResponse> = readTestData("Station_Toei_Mita.json")
            Assert.assertEquals(27, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003102C90", item0.odptId)

            // "2017-12-08T13:40:51+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(13, item0.date.hour)
            Assert.assertEquals(40, item0.date.minute)
            Assert.assertEquals(51, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("目黒", item0.title)
            Assert.assertEquals(139.715456, item0.longitude)
            Assert.assertEquals(35.63297, item0.latitude)
            Assert.assertNull(item0.connectingRailwayIdList)
            Assert.assertNull(item0.exitIdList)
            Assert.assertNull(item0.facilityIdList)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("odpt.Operator:Toei", item0.operator?.id)
            Assert.assertNull(item0.passengerSurveyIdList)
            Assert.assertEquals("odpt.Railway:Toei.Mita", item0.railway?.id)
            Assert.assertEquals("I-01", item0.stationCode)
            Assert.assertNull(item0.stationTimetableIdList)
            Assert.assertEquals("odpt.Station:Toei.Mita.Meguro", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }

        @Test
        fun 東急電鉄_東横線() {
            val list: List<OdptStationResponse> = readTestData("Station_Tokyu_Toyoko.json")
            Assert.assertEquals(21, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003102B34", item0.odptId)

            // "2017-12-08T13:40:53+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(13, item0.date.hour)
            Assert.assertEquals(40, item0.date.minute)
            Assert.assertEquals(53, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("渋谷", item0.title)
            Assert.assertEquals(139.701018, item0.longitude)
            Assert.assertEquals(35.6593, item0.latitude)
            Assert.assertNull(item0.connectingRailwayIdList)
            Assert.assertNull(item0.exitIdList)
            Assert.assertNull(item0.facilityIdList)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("odpt.Operator:Tokyu", item0.operator?.id)
            Assert.assertNull(item0.passengerSurveyIdList)
            Assert.assertEquals("odpt.Railway:Tokyu.Toyoko", item0.railway?.id)
            Assert.assertEquals("TY1", item0.stationCode)
            Assert.assertNull(item0.stationTimetableIdList)
            Assert.assertEquals("odpt.Station:Tokyu.Toyoko.Shibuya", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }

        @Test
        fun 東京臨海高速鉄道() {
            val list: List<OdptStationResponse> = readTestData("Station_TWR.json")
            Assert.assertEquals(8, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003102B2C", item0.odptId)

            // "2017-12-08T13:40:53+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(13, item0.date.hour)
            Assert.assertEquals(40, item0.date.minute)
            Assert.assertEquals(53, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("新木場", item0.title)
            Assert.assertEquals(139.826438, item0.longitude)
            Assert.assertEquals(35.645826, item0.latitude)
            Assert.assertNull(item0.connectingRailwayIdList)
            Assert.assertNull(item0.exitIdList)
            Assert.assertNull(item0.facilityIdList)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("odpt.Operator:TWR", item0.operator?.id)
            Assert.assertNull(item0.passengerSurveyIdList)
            Assert.assertEquals("odpt.Railway:TWR.Rinkai", item0.railway?.id)
            Assert.assertEquals("R1", item0.stationCode)
            Assert.assertNull(item0.stationTimetableIdList)
            Assert.assertEquals("odpt.Station:TWR.Rinkai.ShinKiba", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }

        @Test
        fun 京急_本線() {
            val list: List<OdptStationResponse> = readTestData("Station_Keikyu_Main.json")
            Assert.assertEquals(50, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000031020EF", item0.odptId)

            // "2017-12-08T13:40:51+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(13, item0.date.hour)
            Assert.assertEquals(40, item0.date.minute)
            Assert.assertEquals(51, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("北品川", item0.title)
            Assert.assertEquals(139.739202, item0.longitude)
            Assert.assertEquals(35.622066, item0.latitude)
            Assert.assertNull(item0.connectingRailwayIdList)
            Assert.assertNull(item0.exitIdList)
            Assert.assertNull(item0.facilityIdList)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("odpt.Operator:Keikyu", item0.operator?.id)
            Assert.assertNull(item0.passengerSurveyIdList)
            Assert.assertEquals("odpt.Railway:Keikyu.Main", item0.railway?.id)
            Assert.assertEquals("KK02", item0.stationCode)
            Assert.assertNull(item0.stationTimetableIdList)
            Assert.assertEquals("odpt.Station:Keikyu.Main.Kitashinagawa", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }

        @Test
        fun ゆりかもめ_ゆりかもめ線() {
            val list: List<OdptStationResponse> = readTestData("Station_Yurikamome.json")
            Assert.assertEquals(16, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003102D12", item0.odptId)

            // "2017-12-08T13:40:52+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(13, item0.date.hour)
            Assert.assertEquals(40, item0.date.minute)
            Assert.assertEquals(52, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("新橋", item0.title)
            Assert.assertEquals(139.75672, item0.longitude)
            Assert.assertEquals(35.661548, item0.latitude)
            Assert.assertNull(item0.connectingRailwayIdList)
            Assert.assertNull(item0.exitIdList)
            Assert.assertNull(item0.facilityIdList)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("odpt.Operator:Yurikamome", item0.operator?.id)
            Assert.assertNull(item0.passengerSurveyIdList)
            Assert.assertEquals("odpt.Railway:Yurikamome.Yurikamome", item0.railway?.id)
            Assert.assertEquals("U1", item0.stationCode)
            Assert.assertNull(item0.stationTimetableIdList)
            Assert.assertEquals("odpt.Station:Yurikamome.Yurikamome.Shimbashi", item0.sameAs?.id)
            Assert.assertNull(item0.region)
        }
    }
}