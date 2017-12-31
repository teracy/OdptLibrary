package me.kwsk.odptlibrary.core.api.train

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * 鉄道路線情報のテスト
 * Created by teracy on 2017/12/11.
 */
@RunWith(Enclosed::class)
class OdptRailwayResponseTest {
    // TODO: 2017/12/27 全事業者のデータは揃ったので、以後データに更新があった場合のodptIdとdateのテストについてはnullでないことのみチェックでよい
    @RunWith(AndroidJUnit4::class)
    class デシリアライズのテスト : BaseTest() {
        private fun readTestData(fileName: String): List<OdptRailwayResponse> {
            val gson = getGson()
            val json = readJsonFile(fileName)
            val listType = object : TypeToken<List<OdptRailwayResponse>>() {

            }.type

            return gson.fromJson(json, listType)
        }

        @Test
        fun 東京メトロ() {
            // NOTE: dc:titleに入る路線名は「線」がない（e.g.千代田線->「千代田」）
            val list: List<OdptRailwayResponse> = readTestData("Railway_TokyoMetro.json")
            Assert.assertEquals(10, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000030C46B0", item0.odptId)

            // "2017-12-08T14:10:23+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(14, item0.date.hour)
            Assert.assertEquals(10, item0.date.minute)
            Assert.assertEquals(23, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("千代田", item0.title)
            Assert.assertNull(item0.lineColor)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("C", item0.lineCode)
            Assert.assertEquals("odpt.Operator:TokyoMetro", item0.operator?.id)
            Assert.assertNull(item0.lineSymbol)
            Assert.assertEquals("odpt.Railway:TokyoMetro.Chiyoda", item0.sameAs?.id)
            Assert.assertNull(item0.region)

            Assert.assertEquals(20, item0.stationOrderList.size)
            val stationOrder0 = item0.stationOrderList[0]
            Assert.assertEquals(1, stationOrder0.index)
            Assert.assertEquals("odpt.Station:TokyoMetro.Chiyoda.YoyogiUehara", stationOrder0.station?.id)
            Assert.assertNull(stationOrder0.stationTitle)
        }

        @Test
        fun JR東日本() {
            val list: List<OdptRailwayResponse> = readTestData("Railway_JR-East.json")
            Assert.assertEquals(83, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003100DEC", item0.odptId)

            // "2017-12-06T18:27:01+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(6, item0.date.dayOfMonth)
            Assert.assertEquals(18, item0.date.hour)
            Assert.assertEquals(27, item0.date.minute)
            Assert.assertEquals(1, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("左沢線", item0.title)
            Assert.assertNull(item0.lineColor)
            Assert.assertNull(item0.kana)
            Assert.assertNull(item0.lineCode)
            Assert.assertEquals("odpt.Operator:JR-East", item0.operator?.id)
            Assert.assertNull(item0.lineSymbol)
            Assert.assertEquals("odpt.Railway:JR-East.Aterazawa", item0.sameAs?.id)
            Assert.assertNull(item0.region)

            Assert.assertEquals(12, item0.stationOrderList.size)
            val stationOrder0 = item0.stationOrderList[0]
            Assert.assertEquals(1, stationOrder0.index)
            Assert.assertEquals("odpt.Station:JR-East.Aterazawa.Yamagata", stationOrder0.station?.id)
            Assert.assertEquals("山形", stationOrder0.stationTitle)
        }

        @Test
        fun 都営() {
            // NOTE: API仕様にはない"odpt:travelTime"という項目が存在する上に、定義されている"odpt:TravelTime"と内容が相違する
            val list: List<OdptRailwayResponse> = readTestData("Railway_Toei.json")
            Assert.assertEquals(6, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003100E6F", item0.odptId)

            // "2017-12-08T10:39:44+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(10, item0.date.hour)
            Assert.assertEquals(39, item0.date.minute)
            Assert.assertEquals(44, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("三田線", item0.title)
            Assert.assertNull(item0.lineColor)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("I", item0.lineCode)
            Assert.assertEquals("odpt.Operator:Toei", item0.operator?.id)
            Assert.assertNull(item0.lineSymbol)
            Assert.assertEquals("odpt.Railway:Toei.Mita", item0.sameAs?.id)
            Assert.assertNull(item0.region)

            Assert.assertEquals(27, item0.stationOrderList.size)
            val stationOrder0 = item0.stationOrderList[0]
            Assert.assertEquals(1, stationOrder0.index)
            Assert.assertEquals("odpt.Station:Toei.Mita.Meguro", stationOrder0.station?.id)
            Assert.assertEquals("目黒", stationOrder0.stationTitle)
        }

        @Test
        fun 京王電鉄() {
            val list: List<OdptRailwayResponse> = readTestData("Railway_Keio.json")
            Assert.assertEquals(7, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003100E44", item0.odptId)

            // "2017-12-08T10:41:40+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(10, item0.date.hour)
            Assert.assertEquals(41, item0.date.minute)
            Assert.assertEquals(40, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("京王線", item0.title)
            Assert.assertNull(item0.lineColor)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("KO", item0.lineCode)
            Assert.assertEquals("odpt.Operator:Keio", item0.operator?.id)
            Assert.assertNull(item0.lineSymbol)
            Assert.assertEquals("odpt.Railway:Keio.Keio", item0.sameAs?.id)
            Assert.assertNull(item0.region)

            Assert.assertEquals(32, item0.stationOrderList.size)
            val stationOrder0 = item0.stationOrderList[0]
            Assert.assertEquals(1, stationOrder0.index)
            Assert.assertEquals("odpt.Station:Keio.Keio.Shinjuku", stationOrder0.station?.id)
            Assert.assertEquals("新宿", stationOrder0.stationTitle)
        }

        @Test
        fun 京成電鉄() {
            // NOTE: Odpt:StationOrderのindexは連番でなく、かつ路線によっては開始が1ではない（おそらく全線を通してユニーク）
            val list: List<OdptRailwayResponse> = readTestData("Railway_Keisei.json")
            Assert.assertEquals(7, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003100E4E", item0.odptId)

            // "2017-12-08T10:44:31+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(10, item0.date.hour)
            Assert.assertEquals(44, item0.date.minute)
            Assert.assertEquals(31, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("金町線", item0.title)
            Assert.assertNull(item0.lineColor)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("KS", item0.lineCode)
            Assert.assertEquals("odpt.Operator:Keisei", item0.operator?.id)
            Assert.assertNull(item0.lineSymbol)
            Assert.assertEquals("odpt.Railway:Keisei.Kanamachi", item0.sameAs?.id)
            Assert.assertNull(item0.region)

            Assert.assertEquals(3, item0.stationOrderList.size)
            val stationOrder0 = item0.stationOrderList[0]
            Assert.assertEquals(10, stationOrder0.index)
            Assert.assertEquals("odpt.Station:Keisei.Kanamachi.KeiseiTakasago", stationOrder0.station?.id)
            Assert.assertEquals("京成高砂", stationOrder0.stationTitle)
        }

        @Test
        fun 小田急電鉄() {
            val list: List<OdptRailwayResponse> = readTestData("Railway_Odakyu.json")
            Assert.assertEquals(3, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003100E52", item0.odptId)

            // "2017-12-08T10:51:28+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(10, item0.date.hour)
            Assert.assertEquals(51, item0.date.minute)
            Assert.assertEquals(28, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("小田原線", item0.title)
            Assert.assertNull(item0.lineColor)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("OH", item0.lineCode)
            Assert.assertEquals("odpt.Operator:Odakyu", item0.operator?.id)
            Assert.assertNull(item0.lineSymbol)
            Assert.assertEquals("odpt.Railway:Odakyu.Odawara", item0.sameAs?.id)
            Assert.assertNull(item0.region)

            Assert.assertEquals(47, item0.stationOrderList.size)
            val stationOrder0 = item0.stationOrderList[0]
            Assert.assertEquals(1, stationOrder0.index)
            Assert.assertEquals("odpt.Station:Odakyu.Odawara.Shinjuku", stationOrder0.station?.id)
            Assert.assertEquals("新宿", stationOrder0.stationTitle)
        }

        @Test
        fun 西武鉄道() {
            // NOTE: Odpt:StationOrderのodpt:lineCodeに、明示的に"null"が入っている
            val list: List<OdptRailwayResponse> = readTestData("Railway_Seibu.json")
            Assert.assertEquals(12, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003100E56", item0.odptId)

            // "2017-12-08T10:45:33+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(10, item0.date.hour)
            Assert.assertEquals(45, item0.date.minute)
            Assert.assertEquals(33, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("西武秩父線", item0.title)
            Assert.assertNull(item0.lineColor)
            Assert.assertNull(item0.kana)
            Assert.assertNull(item0.lineCode)
            Assert.assertEquals("odpt.Operator:Seibu", item0.operator?.id)
            Assert.assertNull(item0.lineSymbol)
            Assert.assertEquals("odpt.Railway:Seibu.SeibuChichibu", item0.sameAs?.id)
            Assert.assertNull(item0.region)

            Assert.assertEquals(6, item0.stationOrderList.size)
            val stationOrder0 = item0.stationOrderList[0]
            Assert.assertEquals(1, stationOrder0.index)
            Assert.assertEquals("odpt.Station:Seibu.SeibuChichibu.Agano", stationOrder0.station?.id)
            Assert.assertEquals("吾野", stationOrder0.stationTitle)
        }

        @Test
        fun 東武鉄道() {
            val list: List<OdptRailwayResponse> = readTestData("Railway_Tobu.json")
            Assert.assertEquals(13, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003100E61", item0.odptId)

            // "2017-12-08T10:50:45+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(10, item0.date.hour)
            Assert.assertEquals(50, item0.date.minute)
            Assert.assertEquals(45, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("東武スカイツリーライン", item0.title)
            Assert.assertNull(item0.lineColor)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("TS", item0.lineCode)
            Assert.assertEquals("odpt.Operator:Tobu", item0.operator?.id)
            Assert.assertNull(item0.lineSymbol)
            Assert.assertEquals("odpt.Railway:Tobu.TobuSkytree", item0.sameAs?.id)
            Assert.assertNull(item0.region)

            Assert.assertEquals(30, item0.stationOrderList.size)
            val stationOrder0 = item0.stationOrderList[0]
            Assert.assertEquals(1, stationOrder0.index)
            Assert.assertEquals("odpt.Station:Tobu.TobuSkytree.Asakusa", stationOrder0.station?.id)
            Assert.assertEquals("浅草", stationOrder0.stationTitle)
        }

        @Test
        fun 京急電鉄() {
            val list: List<OdptRailwayResponse> = readTestData("Railway_Keikyu.json")
            Assert.assertEquals(5, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003100E40", item0.odptId)

            // "2017-12-08T10:36:51+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(10, item0.date.hour)
            Assert.assertEquals(36, item0.date.minute)
            Assert.assertEquals(51, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("空港線", item0.title)
            Assert.assertNull(item0.lineColor)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("KK", item0.lineCode)
            Assert.assertEquals("odpt.Operator:Keikyu", item0.operator?.id)
            Assert.assertNull(item0.lineSymbol)
            Assert.assertEquals("odpt.Railway:Keikyu.Airport", item0.sameAs?.id)
            Assert.assertNull(item0.region)

            Assert.assertEquals(6, item0.stationOrderList.size)
            val stationOrder0 = item0.stationOrderList[0]
            Assert.assertEquals(1, stationOrder0.index)
            Assert.assertEquals("odpt.Station:Keikyu.Airport.Kojiya", stationOrder0.station?.id)
            Assert.assertEquals("糀谷", stationOrder0.stationTitle)
        }

        @Test
        fun 東急電鉄() {
            val list: List<OdptRailwayResponse> = readTestData("Railway_Tokyu.json")
            Assert.assertEquals(8, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003100E76", item0.odptId)

            // "2017-12-08T11:34:43+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(11, item0.date.hour)
            Assert.assertEquals(34, item0.date.minute)
            Assert.assertEquals(43, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("東横線", item0.title)
            Assert.assertNull(item0.lineColor)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("TY", item0.lineCode)
            Assert.assertEquals("odpt.Operator:Tokyu", item0.operator?.id)
            Assert.assertNull(item0.lineSymbol)
            Assert.assertEquals("odpt.Railway:Tokyu.Toyoko", item0.sameAs?.id)
            Assert.assertNull(item0.region)

            Assert.assertEquals(21, item0.stationOrderList.size)
            val stationOrder0 = item0.stationOrderList[0]
            Assert.assertEquals(1, stationOrder0.index)
            Assert.assertEquals("odpt.Station:Tokyu.Toyoko.Shibuya", stationOrder0.station?.id)
            Assert.assertEquals("渋谷", stationOrder0.stationTitle)
        }

        @Test
        fun 東京臨海高速鉄道() {
            // NOTE: dc:titleに入る路線名は「線」がない（りんかい線->「りんかい」）
            // FIXME: 2017/12/8作成のデータではodpt.railwayの値が"odpt.Railway:odpt.Station:TWR.Rinkai"なのでinvalidである。正しくは"odpt.Railway:TWR.Rinkai"のはず
            val list: List<OdptRailwayResponse> = readTestData("Railway_TWR.json")
            Assert.assertEquals(1, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003100E75", item0.odptId)

            // "2017-12-08T11:26:43+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(11, item0.date.hour)
            Assert.assertEquals(26, item0.date.minute)
            Assert.assertEquals(43, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("りんかい", item0.title)
            Assert.assertNull(item0.lineColor)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("R", item0.lineCode)
            Assert.assertEquals("odpt.Operator:TWR", item0.operator?.id)
            Assert.assertNull(item0.lineSymbol)
            Assert.assertEquals("odpt.Railway:odpt.Station:TWR.Rinkai", item0.sameAs?.id)
            Assert.assertNull(item0.region)

            Assert.assertEquals(8, item0.stationOrderList.size)
            val stationOrder0 = item0.stationOrderList[0]
            Assert.assertEquals(1, stationOrder0.index)
            Assert.assertEquals("odpt.Station:TWR.Rinkai.ShinKiba", stationOrder0.station?.id)
            Assert.assertEquals("新木場", stationOrder0.stationTitle)
        }

        @Test
        fun ゆりかもめ() {
            val list: List<OdptRailwayResponse> = readTestData("Railway_Yurikamome.json")
            Assert.assertEquals(1, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C00000000000001000003100E74", item0.odptId)

            // "2017-12-08T10:43:34+09:00"
            Assert.assertEquals(2017, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(10, item0.date.hour)
            Assert.assertEquals(43, item0.date.minute)
            Assert.assertEquals(34, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals("ゆりかもめ", item0.title)
            Assert.assertNull(item0.lineColor)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("U", item0.lineCode)
            Assert.assertEquals("odpt.Operator:Yurikamome", item0.operator?.id)
            Assert.assertNull(item0.lineSymbol)
            Assert.assertEquals("odpt.Railway:Yurikamome.Yurikamome", item0.sameAs?.id)
            Assert.assertNull(item0.region)

            Assert.assertEquals(16, item0.stationOrderList.size)
            val stationOrder0 = item0.stationOrderList[0]
            Assert.assertEquals(1, stationOrder0.index)
            Assert.assertEquals("odpt.Station:Yurikamome.Yurikamome.Shimbashi", stationOrder0.station?.id)
            Assert.assertEquals("新橋", stationOrder0.stationTitle)
        }

        @Test
        fun 小田急電鉄_江ノ島線() {
            // NOTE: 鵠沼海岸駅及び本鵠沼駅のテスト（「鵠」の字が豆腐になっていないか）
            val list: List<OdptRailwayResponse> = readTestData("Railway_Odakyu_Enoshima.json")
            Assert.assertEquals(1, list.size)
            val item0 = list[0]
            // odptIdとdateはNotNullであることだけテスト
            Assert.assertNotNull(item0.odptId)
            Assert.assertNotNull(item0.date)

            Assert.assertEquals("江ノ島線", item0.title)
            Assert.assertNull(item0.lineColor)
            Assert.assertNull(item0.kana)
            Assert.assertEquals("OE", item0.lineCode)
            Assert.assertEquals("odpt.Operator:Odakyu", item0.operator?.id)
            Assert.assertNull(item0.lineSymbol)
            Assert.assertEquals("odpt.Railway:Odakyu.Enoshima", item0.sameAs?.id)
            Assert.assertNull(item0.region)

            Assert.assertEquals(16, item0.stationOrderList.size)
            // 1本鵠沼駅と鵠沼海岸駅のみテスト（2017/12/27現在、本鵠沼のstationTitleが怪しい）
            val stationOrder14 = item0.stationOrderList[14]
            Assert.assertEquals(15, stationOrder14.index)
            Assert.assertEquals("odpt.Station:Odakyu.Enoshima.KugenumaKaigan", stationOrder14.station?.id)
            Assert.assertEquals("鵠沼海岸", stationOrder14.stationTitle)
            val stationOrder13 = item0.stationOrderList[13]
            Assert.assertEquals(14, stationOrder13.index)
            Assert.assertEquals("odpt.Station:Odakyu.Enoshima.HonKugenuma", stationOrder13.station?.id)
            // FIXME: 2017/12/27現在、小田急電鉄江ノ島線の本鵠沼駅の「鵠」が豆腐になっている
//            Assert.assertEquals("本鵠沼", stationOrder13.stationTitle)
        }
    }
}