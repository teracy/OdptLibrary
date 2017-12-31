package me.kwsk.odptlibrary.core.api.train

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import me.kwsk.odptlibrary.core.api.code.RailwayToei
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * 列車運行情報のテスト
 * Created by teracy on 2017/12/11.
 */
@RunWith(Enclosed::class)
class OdptTrainInformationResponseTest {
    @RunWith(Enclosed::class)
    class デシリアライズのテスト {
        abstract class BaseDeserializeTest : BaseTest() {
            fun readTestData(fileName: String): List<OdptTrainInformationResponse> {
                val gson = getGson()
                val json = readJsonFile(fileName)
                val listType = object : TypeToken<List<OdptTrainInformationResponse>>() {

                }.type

                return gson.fromJson(json, listType)
            }
        }

        @RunWith(AndroidJUnit4::class)
        class 東京メトロ : BaseDeserializeTest() {
            // NOTE: 東京メトロは支障があろうがなかろうが全線の情報が表示される
            @Test
            fun 全線支障なし() {
                val list: List<OdptTrainInformationResponse> = readTestData("TrainInformation_TokyoMetro.json")
                Assert.assertEquals(9, list.size)
                val item0 = list[0]
                Assert.assertEquals("urn:ucode:_00001C000000000000010000030C3BE5", item0.odptId)

                // "2017-12-11T13:15:05+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(11, item0.date.dayOfMonth)
                Assert.assertEquals(13, item0.date.hour)
                Assert.assertEquals(15, item0.date.minute)
                Assert.assertEquals(5, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                // "2017-12-11T13:20:05+09:00"
                Assert.assertEquals(2017, item0.valid?.year)
                Assert.assertEquals(12, item0.valid?.month?.value)
                Assert.assertEquals(11, item0.valid?.dayOfMonth)
                Assert.assertEquals(13, item0.valid?.hour)
                Assert.assertEquals(20, item0.valid?.minute)
                Assert.assertEquals(5, item0.valid?.second)
                Assert.assertEquals("+09:00", item0.valid?.zone?.id)

                Assert.assertEquals("odpt.Operator:TokyoMetro", item0.operator?.id)
                Assert.assertEquals("odpt.Railway:TokyoMetro.Hanzomon", item0.railway?.id)
                Assert.assertNull(item0.railwayTitle)
                Assert.assertNull(item0.resumeEstimate)
                Assert.assertNull(item0.stationFrom)
                Assert.assertNull(item0.stationTo)

                // "2017-12-05T11:16:00+09:00"
                Assert.assertEquals(2017, item0.timeOfOrigin?.year)
                Assert.assertEquals(12, item0.timeOfOrigin?.month?.value)
                Assert.assertEquals(5, item0.timeOfOrigin?.dayOfMonth)
                Assert.assertEquals(11, item0.timeOfOrigin?.hour)
                Assert.assertEquals(16, item0.timeOfOrigin?.minute)
                Assert.assertEquals(0, item0.timeOfOrigin?.second)
                Assert.assertEquals("+09:00", item0.timeOfOrigin?.zone?.id)

                Assert.assertNull(item0.trainInformationArea)
                Assert.assertNull(item0.trainInformationCause)
                Assert.assertNull(item0.trainInformationKind)
                Assert.assertNull(item0.trainInformationLine)
                Assert.assertNull(item0.trainInformationRange)
                Assert.assertNull(item0.trainInformationStatus)
                Assert.assertEquals("現在、平常どおり運転しています。", item0.trainInformationText)
                Assert.assertNull(item0.transferRailways)
            }
        }

        @RunWith(AndroidJUnit4::class)
        class JR東日本 : BaseDeserializeTest() {
            // TODO: エリアが広いのと冬季なので全くどこにも支障がない状態というのを取得するのが難しそうだが、全線が平常運行の場合のデータを採取する
            // NOTE: JR東日本は支障がない路線は表示されない？
            // NOTE: API仕様にはない"odpt:railwayTitle"という項目が存在する
            // NOTE: "odpt:stationTo"がない代わりに、API仕様にはない"odpt:stationToTitle"という項目が存在する（路線名ではなく路線IDが入っている）
            // NOTE: "odpt:stationFrom"がない代わりに、API仕様にはない"odpt:stationFromTitle"という項目が存在する（路線名ではなく路線IDが入っている）
            // NOTE: "odpt:trainInformationLine"がない代わりに、API仕様にはない"odpt:trainInformationLineTitle"という項目が存在する
            // NOTE: "odpt:trainInformationCause"がない代わりに、API仕様にはない"odpt:trainInformationCauseTitle"という項目が存在する
            // NOTE: "odpt:trainInformationStatus"と同じ内容が入っている、API仕様にはない"odpt:trainInformationStatusTitle"という項目が存在する
            @Test
            fun 支障あり() {
                val list: List<OdptTrainInformationResponse> = readTestData("TrainInformation_JR-East.json")
                Assert.assertEquals(1, list.size)
                val item0 = list[0]
                Assert.assertEquals("urn:uuid:f6e840a1-ccd9-46f7-a88a-8e542d640ec8", item0.odptId)

                // "2017-12-11T11:54:00+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(11, item0.date.dayOfMonth)
                Assert.assertEquals(11, item0.date.hour)
                Assert.assertEquals(54, item0.date.minute)
                Assert.assertEquals(0, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                Assert.assertNull(item0.valid)
                Assert.assertEquals("odpt.Operator:JR-East", item0.operator?.id)
                Assert.assertEquals("odpt.Railway:JR-East.Uetsu", item0.railway?.id)
                Assert.assertEquals("羽越本線", item0.railwayTitle)
                Assert.assertNull(item0.resumeEstimate)
                Assert.assertEquals("odpt.Station:JR-East.Uetsu.Sakata", item0.stationFrom?.id)
                Assert.assertEquals("odpt.Station:JR-East.Uetsu.Akita", item0.stationTo?.id)

                // "2017-12-11T11:53:58+09:00"
                Assert.assertEquals(2017, item0.timeOfOrigin?.year)
                Assert.assertEquals(12, item0.timeOfOrigin?.month?.value)
                Assert.assertEquals(11, item0.timeOfOrigin?.dayOfMonth)
                Assert.assertEquals(11, item0.timeOfOrigin?.hour)
                Assert.assertEquals(53, item0.timeOfOrigin?.minute)
                Assert.assertEquals(58, item0.timeOfOrigin?.second)
                Assert.assertEquals("+09:00", item0.timeOfOrigin?.zone?.id)

                Assert.assertNull(item0.trainInformationArea)
                Assert.assertEquals("強風", item0.trainInformationCause)
                Assert.assertNull(item0.trainInformationKind)
                Assert.assertEquals("上下線", item0.trainInformationLine)
                Assert.assertNull(item0.trainInformationRange)
                Assert.assertEquals("遅延", item0.trainInformationStatus)
                Assert.assertEquals("羽越本線は、強風の影響で、酒田〜秋田駅間の上下線の一部列車に遅れがでています。", item0.trainInformationText)
                Assert.assertNull(item0.transferRailways)
            }
        }

        @RunWith(AndroidJUnit4::class)
        class 都営 : BaseDeserializeTest() {
            // NOTE: 都営は支障のない路線全線の情報が表示される
            // NOTE: API仕様上でrequiredである"odpt:timeOfOrigin"が存在しない
            // NOTE: API仕様にはない"odpt:railwayTitle"という項目が存在する
            @Test
            fun 全線支障なし() {
                val list: List<OdptTrainInformationResponse> = readTestData("TrainInformation_Toei.json")
                Assert.assertEquals(6, list.size)
                val item0 = list[0]
                Assert.assertEquals("urn:uuid:e2ffab1c-c217-452a-8103-2244bb875102", item0.odptId)

                // "2017-12-22T14:24:03+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(22, item0.date.dayOfMonth)
                Assert.assertEquals(14, item0.date.hour)
                Assert.assertEquals(24, item0.date.minute)
                Assert.assertEquals(3, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                // "2017-12-22T14:27:03+09:00"
                Assert.assertEquals(2017, item0.valid?.year)
                Assert.assertEquals(12, item0.valid?.month?.value)
                Assert.assertEquals(22, item0.valid?.dayOfMonth)
                Assert.assertEquals(14, item0.valid?.hour)
                Assert.assertEquals(27, item0.valid?.minute)
                Assert.assertEquals(3, item0.valid?.second)
                Assert.assertEquals("+09:00", item0.valid?.zone?.id)

                Assert.assertEquals("odpt.Operator:Toei", item0.operator?.id)
                Assert.assertEquals("odpt.Railway:Toei.Asakusa", item0.railway?.id)
                Assert.assertEquals("浅草線", item0.railwayTitle)
                Assert.assertNull(item0.resumeEstimate)
                Assert.assertNull(item0.stationFrom)
                Assert.assertNull(item0.stationTo)

                // API仕様上requiredであるがデータが存在しない
                Assert.assertNull(item0.timeOfOrigin)

                Assert.assertNull(item0.trainInformationArea)
                Assert.assertNull(item0.trainInformationCause)
                Assert.assertNull(item0.trainInformationKind)
                Assert.assertNull(item0.trainInformationLine)
                Assert.assertNull(item0.trainInformationRange)
                Assert.assertNull(item0.trainInformationStatus)
                Assert.assertEquals("現在、１５分以上の遅延はありません。", item0.trainInformationText)
                Assert.assertNull(item0.transferRailways)

                // 全線が取れていることを確認
                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.MITA.id })
                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.OEDO.id })
                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.ARAKAWA.id })
                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.NIPPORI_TONERI.id })
                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.ASAKUSA.id })
                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.SHINJUKU.id })
            }

            @Test
            fun 三田線支障あり() {
                val list: List<OdptTrainInformationResponse> = readTestData("TrainInformation_Toei_MitaAccident.json")
                Assert.assertEquals(5, list.size)
                val item0 = list[0]
                Assert.assertEquals("urn:uuid:970884f7-9e5b-414d-8345-7b5316862162", item0.odptId)

                // "2017-12-16T22:24:13+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(16, item0.date.dayOfMonth)
                Assert.assertEquals(22, item0.date.hour)
                Assert.assertEquals(24, item0.date.minute)
                Assert.assertEquals(13, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                // "2017-12-16T22:27:13+09:00"
                Assert.assertEquals(2017, item0.valid?.year)
                Assert.assertEquals(12, item0.valid?.month?.value)
                Assert.assertEquals(16, item0.valid?.dayOfMonth)
                Assert.assertEquals(22, item0.valid?.hour)
                Assert.assertEquals(27, item0.valid?.minute)
                Assert.assertEquals(13, item0.valid?.second)
                Assert.assertEquals("+09:00", item0.valid?.zone?.id)

                Assert.assertEquals("odpt.Operator:Toei", item0.operator?.id)
                Assert.assertEquals("odpt.Railway:Toei.Asakusa", item0.railway?.id)
                Assert.assertEquals("浅草線", item0.railwayTitle)
                Assert.assertNull(item0.resumeEstimate)
                Assert.assertNull(item0.stationFrom)
                Assert.assertNull(item0.stationTo)

                // API仕様上requiredであるがデータが存在しない
                Assert.assertNull(item0.timeOfOrigin)

                Assert.assertNull(item0.trainInformationArea)
                Assert.assertNull(item0.trainInformationCause)
                Assert.assertNull(item0.trainInformationKind)
                Assert.assertNull(item0.trainInformationLine)
                Assert.assertNull(item0.trainInformationRange)
                Assert.assertNull(item0.trainInformationStatus)
                Assert.assertEquals("現在、１５分以上の遅延はありません。", item0.trainInformationText)
                Assert.assertNull(item0.transferRailways)

                // 三田線以外全線が取れていることを確認
                Assert.assertNull(list.find { it.railway?.id == RailwayToei.MITA.id })
                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.OEDO.id })
//                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.ARAKAWA.id })
                Assert.assertNull(list.find { it.railway?.id == RailwayToei.ARAKAWA.id })
                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.NIPPORI_TONERI.id })
                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.ASAKUSA.id })
                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.SHINJUKU.id })
            }

            @Test
            fun 浅草線大江戸線支障あり() {
                val list: List<OdptTrainInformationResponse> = readTestData("TrainInformation_Toei_AsakusaOedoAccident.json")
                Assert.assertEquals(4, list.size)
                val item0 = list[0]
                Assert.assertEquals("urn:uuid:f4958b5c-b47f-4232-9e22-9f30cae5ec45", item0.odptId)

                // "2017-12-17T12:03:29+09:00"
                Assert.assertEquals(2017, item0.date.year)
                Assert.assertEquals(12, item0.date.month.value)
                Assert.assertEquals(17, item0.date.dayOfMonth)
                Assert.assertEquals(12, item0.date.hour)
                Assert.assertEquals(3, item0.date.minute)
                Assert.assertEquals(29, item0.date.second)
                Assert.assertEquals("+09:00", item0.date.zone.id)

                // "2017-12-17T12:06:29+09:00"
                Assert.assertEquals(2017, item0.valid?.year)
                Assert.assertEquals(12, item0.valid?.month?.value)
                Assert.assertEquals(17, item0.valid?.dayOfMonth)
                Assert.assertEquals(12, item0.valid?.hour)
                Assert.assertEquals(6, item0.valid?.minute)
                Assert.assertEquals(29, item0.valid?.second)
                Assert.assertEquals("+09:00", item0.valid?.zone?.id)

                Assert.assertEquals("odpt.Operator:Toei", item0.operator?.id)
                Assert.assertEquals("odpt.Railway:Toei.Mita", item0.railway?.id)
                Assert.assertNull(item0.resumeEstimate)
                Assert.assertNull(item0.stationFrom)
                Assert.assertNull(item0.stationTo)

                // API仕様上requiredであるがデータが存在しない
                Assert.assertNull(item0.timeOfOrigin)

                Assert.assertNull(item0.trainInformationArea)
                Assert.assertNull(item0.trainInformationCause)
                Assert.assertNull(item0.trainInformationKind)
                Assert.assertNull(item0.trainInformationLine)
                Assert.assertNull(item0.trainInformationRange)
                Assert.assertNull(item0.trainInformationStatus)
                Assert.assertEquals("現在、１５分以上の遅延はありません。", item0.trainInformationText)
                Assert.assertNull(item0.transferRailways)

                // 浅草線と大江戸線以外全線が取れていることを確認
                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.MITA.id })
                Assert.assertNull(list.find { it.railway?.id == RailwayToei.OEDO.id })
//                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.TODEN_ARAKAWAid })
                Assert.assertNull(list.find { it.railway?.id == RailwayToei.ARAKAWA.id })
                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.NIPPORI_TONERI.id })
                Assert.assertNull(list.find { it.railway?.id == RailwayToei.ASAKUSA.id })
                Assert.assertNotNull(list.find { it.railway?.id == RailwayToei.SHINJUKU.id })
            }
        }
    }
}