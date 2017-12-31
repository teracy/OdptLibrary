package me.kwsk.odptlibrary.core.api.train

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * 列車情報のテスト
 * Created by teracy on 2017/12/11.
 */
@RunWith(Enclosed::class)
class OdptTrainResponseTest {
    @RunWith(Enclosed::class)
    class デシリアライズのテスト {
        abstract class BaseDeserializeTest : BaseTest() {
            fun readTestData(fileName: String): List<OdptTrainResponse> {
                val gson = getGson()
                val json = readJsonFile(fileName)
                val listType = object : TypeToken<List<OdptTrainResponse>>() {

                }.type

                return gson.fromJson(json, listType)
            }
        }

        @RunWith(AndroidJUnit4::class)
        class 東京メトロ : BaseDeserializeTest() {
            // NOTE: idの形式はucode
            // NOTE: API仕様にはない"dct:valid"という項目が存在する
            // NOTE: API仕様にはない"odpt:operator"という項目が存在する
            // NOTE: API仕様にはない"odpt:frequency"という項目が存在する
            @Test
            fun 銀座線() {
                val list: List<OdptTrainResponse> = readTestData("Train_TokyoMetro_Ginza_20171215_155042.json")
                Assert.assertEquals(25, list.size)
                val item0 = list[0]
                Assert.assertEquals("urn:ucode:_00001C00000000000001000003101DF3", item0.odptId)

                // "2017-12-15T15:50:42+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(15, item0.date.dayOfMonth)
                Assert.assertEquals(15, item0.date.hour)
                Assert.assertEquals(50, item0.date.minute)
                Assert.assertEquals(42, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                Assert.assertEquals(0, item0.delay)
                Assert.assertEquals("odpt.Station:TokyoMetro.Ginza.Asakusa", item0.fromStation?.id)
                Assert.assertNull(item0.fromStationTitle)
                Assert.assertEquals("odpt.RailDirection:TokyoMetro.Shibuya", item0.railDirection?.id)
                Assert.assertEquals("odpt.Railway:TokyoMetro.Ginza", item0.railway?.id)
                Assert.assertEquals("odpt.Station:TokyoMetro.Ginza.Asakusa", item0.startingStation?.id)
                Assert.assertEquals("odpt.Station:TokyoMetro.Ginza.Shibuya", item0.terminalStation?.id)
                Assert.assertNull(item0.toStation)
                Assert.assertNull(item0.toStationTitle)
                Assert.assertEquals("A1551", item0.trainNumber)
                Assert.assertEquals("odpt.TrainOwner:TokyoMetro", item0.trainOwner?.id)
                Assert.assertEquals("odpt.TrainType:TokyoMetro.Local", item0.trainType?.id)
                Assert.assertNull(item0.trainTypeTitle)
                Assert.assertEquals("odpt.Train:TokyoMetro.Ginza.A1551", item0.sameAs?.id)

                val item1 = list[1]
                Assert.assertEquals("urn:ucode:_00001C00000000000001000003101DF6", item1.odptId)

                // "2017-12-15T15:50:42+09:00"
                Assert.assertEquals(2017, item1.date.year)
                Assert.assertEquals(12, item1.date.month.value)
                Assert.assertEquals(15, item1.date.dayOfMonth)
                Assert.assertEquals(15, item1.date.hour)
                Assert.assertEquals(50, item1.date.minute)
                Assert.assertEquals(42, item1.date.second)
                Assert.assertEquals("+09:00", item1.date.zone.id)

                Assert.assertEquals(0, item1.delay)
                Assert.assertEquals("odpt.Station:TokyoMetro.Ginza.Inaricho", item1.fromStation?.id)
                Assert.assertNull(item1.fromStationTitle)
                Assert.assertEquals("odpt.RailDirection:TokyoMetro.Shibuya", item1.railDirection?.id)
                Assert.assertEquals("odpt.Railway:TokyoMetro.Ginza", item1.railway?.id)
                Assert.assertEquals("odpt.Station:TokyoMetro.Ginza.Asakusa", item1.startingStation?.id)
                Assert.assertEquals("odpt.Station:TokyoMetro.Ginza.Shibuya", item1.terminalStation?.id)
                Assert.assertNull(item1.toStation)
                Assert.assertNull(item1.toStationTitle)
                Assert.assertEquals("A1549", item1.trainNumber)
                Assert.assertEquals("odpt.TrainOwner:TokyoMetro", item1.trainOwner?.id)
                Assert.assertEquals("odpt.TrainType:TokyoMetro.Local", item1.trainType?.id)
                Assert.assertNull(item1.trainTypeTitle)
                Assert.assertEquals("odpt.Train:TokyoMetro.Ginza.A1549", item1.sameAs?.id)
            }

            @Test
            fun 東西線() {
                val list: List<OdptTrainResponse> = readTestData("Train_TokyoMetro_Tozai_20171227_162124.json")
                Assert.assertEquals(25, list.size)
                val item0 = list[0]
                Assert.assertEquals("urn:ucode:_00001C000000000000010000030FB998", item0.odptId)

                // "2017-12-27T16:21:24+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(27, item0.date.dayOfMonth)
                Assert.assertEquals(16, item0.date.hour)
                Assert.assertEquals(21, item0.date.minute)
                Assert.assertEquals(24, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                Assert.assertEquals(0, item0.delay)
                Assert.assertEquals("odpt.Station:TokyoMetro.Tozai.Nakano", item0.fromStation?.id)
                Assert.assertNull(item0.fromStationTitle)
                Assert.assertEquals("odpt.RailDirection:TokyoMetro.NishiFunabashi", item0.railDirection?.id)
                Assert.assertEquals("odpt.Railway:TokyoMetro.Tozai", item0.railway?.id)
                Assert.assertEquals("odpt.Station:TokyoMetro.Tozai.Nakano", item0.startingStation?.id)
                Assert.assertEquals("odpt.Station:TokyoMetro.Tozai.NishiFunabashi", item0.terminalStation?.id)
                Assert.assertNull(item0.toStation)
                Assert.assertNull(item0.toStationTitle)
                Assert.assertEquals("A1685S", item0.trainNumber)
                Assert.assertEquals("odpt.TrainOwner:TokyoMetro", item0.trainOwner?.id)
                Assert.assertEquals("odpt.TrainType:TokyoMetro.Local", item0.trainType?.id)
                Assert.assertNull(item0.trainTypeTitle)
                Assert.assertEquals("odpt.Train:TokyoMetro.Tozai.A1685S", item0.sameAs?.id)

                val item1 = list[1]
                Assert.assertEquals("urn:ucode:_00001C000000000000010000030FB937", item1.odptId)

                // "2017-12-27T16:21:24+09:00"
                Assert.assertEquals(2017, item1.date.year)
                Assert.assertEquals(12, item1.date.month.value)
                Assert.assertEquals(27, item1.date.dayOfMonth)
                Assert.assertEquals(16, item1.date.hour)
                Assert.assertEquals(21, item1.date.minute)
                Assert.assertEquals(24, item1.date.second)
                Assert.assertEquals("+09:00", item1.date.zone.id)

                Assert.assertEquals(0, item1.delay)
                Assert.assertEquals("odpt.Station:TokyoMetro.Tozai.Nakano", item1.fromStation?.id)
                Assert.assertNull(item1.fromStationTitle)
                Assert.assertEquals("odpt.RailDirection:TokyoMetro.NishiFunabashi", item1.railDirection?.id)
                Assert.assertEquals("odpt.Railway:TokyoMetro.Tozai", item1.railway?.id)
                Assert.assertEquals("odpt.Station:JR-East.Chuo.Mitaka", item1.startingStation?.id)
                Assert.assertEquals("odpt.Station:TokyoMetro.Tozai.NishiFunabashi", item1.terminalStation?.id)
                Assert.assertEquals("odpt.Station:TokyoMetro.Tozai.Ochiai", item1.toStation?.id)
                Assert.assertNull(item1.toStationTitle)
                Assert.assertEquals("A1633S", item1.trainNumber)
                Assert.assertEquals("odpt.TrainOwner:TokyoMetro", item1.trainOwner?.id)
                Assert.assertEquals("odpt.TrainType:TokyoMetro.Local", item1.trainType?.id)
                Assert.assertNull(item1.trainTypeTitle)
                Assert.assertEquals("odpt.Train:TokyoMetro.Tozai.A1633S", item1.sameAs?.id)
            }
        }

        @RunWith(AndroidJUnit4::class)
        class JR東日本 : BaseDeserializeTest() {
            // NOTE: idの形式はuuid
            // NOTE: API仕様にはない"odpt:index"という項目が存在する
            // NOTE: API仕様にはない"odpt:operator"という項目が存在する
            // NOTE: "odpt:toStation"に明示的に"null"が入れられているデータがある
            // NOTE: 2017/12/8作成のデータではodpt.railDirectionの値が"odpt.RailwayDirection:Inbound"なのでinvalidである。正しくは"odpt.RailDirection:Inbound"のはず
            // NOTE: API仕様にはない"odpt:carComposition"という項目が存在する
            // NOTE: API仕様にはない"odpt:toStationTitle"という項目が存在する
            // NOTE: "odpt:trainType"がない代わりにAPI仕様にはない"odpt:trainTypeTitle"という項目が存在する
            // NOTE: API仕様にはない"odpt:fromStationTitle"という項目が存在する
            // NOTE: API仕様にはない"odpt:destinationStation"という項目が存在する
            @Test
            fun 高崎線() {
                val list: List<OdptTrainResponse> = readTestData("Train_JR-East_Takasaki_20171215_154734.json")
                Assert.assertEquals(10, list.size)

                val item0 = list[0]
                Assert.assertEquals("urn:uuid:79a8fb3e-ed33-41f7-ae89-767b6ca18dfd", item0.odptId)

                // "2017-12-15T15:47:34+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(15, item0.date.dayOfMonth)
                Assert.assertEquals(15, item0.date.hour)
                Assert.assertEquals(47, item0.date.minute)
                Assert.assertEquals(34, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                Assert.assertEquals(0, item0.delay)
                Assert.assertEquals("odpt.Station:JR-East.Takasaki.Oku", item0.fromStation?.id)
                Assert.assertEquals("尾久", item0.fromStationTitle)
                Assert.assertEquals("odpt.RailwayDirection:Inbound", item0.railDirection?.id)
                Assert.assertEquals("odpt.Railway:JR-East.Takasaki", item0.railway?.id)
                Assert.assertNull(item0.startingStation)
                Assert.assertNull(item0.terminalStation)
                Assert.assertNull(item0.toStation)
                Assert.assertNull(item0.toStationTitle)
                Assert.assertEquals("9883M", item0.trainNumber)
                Assert.assertNull(item0.trainOwner)
                Assert.assertNull(item0.trainType)
                Assert.assertEquals("団体扱い", item0.trainTypeTitle)
                Assert.assertEquals("odpt.Train:JR-East.Takasaki.9883M", item0.sameAs?.id)

                val item1 = list[1]
                Assert.assertEquals("urn:uuid:5aae9151-8ab9-45d7-834b-a8dce290e5fe", item1.odptId)

                // "2017-12-15T15:47:34+09:00"
                Assert.assertEquals(2017, item1.date.year)
                Assert.assertEquals(12, item1.date.month.value)
                Assert.assertEquals(15, item1.date.dayOfMonth)
                Assert.assertEquals(15, item1.date.hour)
                Assert.assertEquals(47, item1.date.minute)
                Assert.assertEquals(34, item1.date.second)
                Assert.assertEquals("+09:00", item1.date.zone.id)

                Assert.assertEquals(0, item1.delay)
                Assert.assertEquals("odpt.Station:JR-East.Takasaki.Miyahara", item1.fromStation?.id)
                Assert.assertEquals("宮原", item1.fromStationTitle)
                Assert.assertEquals("odpt.RailwayDirection:Inbound", item1.railDirection?.id)
                Assert.assertEquals("odpt.Railway:JR-East.Takasaki", item1.railway?.id)
                Assert.assertNull(item1.startingStation)
                Assert.assertNull(item1.terminalStation)
                Assert.assertEquals("odpt.Station:JR-East.Takasaki.Omiya", item1.toStation?.id)
                Assert.assertEquals("大宮", item1.toStationTitle)
                Assert.assertEquals("844M", item1.trainNumber)
                Assert.assertNull(item1.trainOwner)
                Assert.assertNull(item1.trainType)
                Assert.assertEquals("普通", item1.trainTypeTitle)
                Assert.assertEquals("odpt.Train:JR-East.Takasaki.844M", item1.sameAs?.id)
            }

            @Test
            fun 中央快速線() {
                val list: List<OdptTrainResponse> = readTestData("Train_JR-East_ChuoRapid_20171227_155333.json")
                Assert.assertEquals(66, list.size)

                val item0 = list[0]
                Assert.assertEquals("urn:uuid:0fb0d6e8-1bbc-4d0a-b716-82662a61bf3a", item0.odptId)

                // "2017-12-27T15:53:33+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(27, item0.date.dayOfMonth)
                Assert.assertEquals(15, item0.date.hour)
                Assert.assertEquals(53, item0.date.minute)
                Assert.assertEquals(33, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                Assert.assertEquals(60, item0.delay)
                Assert.assertEquals("odpt.Station:JR-East.ChuoRapid.Mitaka", item0.fromStation?.id)
                Assert.assertEquals("三鷹", item0.fromStationTitle)
                Assert.assertEquals("odpt.RailwayDirection:Outbound", item0.railDirection?.id)
                Assert.assertEquals("odpt.Railway:JR-East.ChuoRapid", item0.railway?.id)
                Assert.assertNull(item0.startingStation)
                Assert.assertNull(item0.terminalStation)
                Assert.assertNull(item0.toStation)
                Assert.assertNull(item0.toStationTitle)
                Assert.assertEquals("1579T", item0.trainNumber)
                Assert.assertNull(item0.trainOwner)
                Assert.assertNull(item0.trainType)
                Assert.assertEquals("各駅停車", item0.trainTypeTitle)
                Assert.assertEquals("odpt.Train:JR-East.ChuoRapid.1579T", item0.sameAs?.id)

                val item1 = list[1]
                Assert.assertEquals("urn:uuid:f70dfc02-453b-485a-9244-5d5a63e6fa63", item1.odptId)

                // "2017-12-27T15:53:33+09:00"
                Assert.assertEquals(2017, item1.date.year)
                Assert.assertEquals(12, item1.date.month.value)
                Assert.assertEquals(27, item1.date.dayOfMonth)
                Assert.assertEquals(15, item1.date.hour)
                Assert.assertEquals(53, item1.date.minute)
                Assert.assertEquals(33, item1.date.second)
                Assert.assertEquals("+09:00", item1.date.zone.id)

                Assert.assertEquals(0, item1.delay)
                Assert.assertEquals("odpt.Station:JR-East.ChuoRapid.Yotsuya", item1.fromStation?.id)
                Assert.assertEquals("四ツ谷", item1.fromStationTitle)
                Assert.assertEquals("odpt.RailwayDirection:Outbound", item1.railDirection?.id)
                Assert.assertEquals("odpt.Railway:JR-East.ChuoRapid", item1.railway?.id)
                Assert.assertNull(item1.startingStation)
                Assert.assertNull(item1.terminalStation)
                Assert.assertEquals("odpt.Station:JR-East.ChuoRapid.Shinjuku", item1.toStation?.id)
                Assert.assertEquals("新宿", item1.toStationTitle)
                Assert.assertEquals("1523H", item1.trainNumber)
                Assert.assertNull(item1.trainOwner)
                Assert.assertNull(item1.trainType)
                Assert.assertEquals("快速", item1.trainTypeTitle)
                Assert.assertEquals("odpt.Train:JR-East.ChuoRapid.1523H", item1.sameAs?.id)
            }
        }
    }
}