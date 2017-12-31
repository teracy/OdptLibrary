package me.kwsk.odptlibrary.core.api.train

import android.support.test.runner.AndroidJUnit4
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * 運賃情報のテスト
 * Created by teracy on 2017/12/11.
 */
@RunWith(Enclosed::class)
class OdptRailwayFareResponseTest {
    @RunWith(AndroidJUnit4::class)
    class デシリアライズのテスト : BaseTest() {
        private fun readTestData(fileName: String): List<OdptRailwayFareResponse> {
            val gson = getGson()
            val json = readJsonFile(fileName)
            val listType = object : TypeToken<List<OdptRailwayFareResponse>>() {

            }.type

            return gson.fromJson(json, listType)
        }

        @Test
        fun 東武東上線大山発_東武東上線和光市着() {
            // NOTE: 2017/12/18作成のデータでは"owl.sameAs"の値が仕様に従っていない：正しくは"odpt.RailwayFare:Tobu.Tojo.Uyama.Tobu.Tojo.Wakoshi"のはず
            val list: List<OdptRailwayFareResponse> = readTestData("RailwayFare_Tobu_TojoUyama_TojoWakoshi.json")
            Assert.assertEquals(1, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000030EFFB4", item0.odptId)

            // "2016-12-12T17:53:50+09:00"
            Assert.assertEquals(2016, item0.date.year)
            Assert.assertEquals(12, item0.date.month.value)
            Assert.assertEquals(12, item0.date.dayOfMonth)
            Assert.assertEquals(17, item0.date.hour)
            Assert.assertEquals(53, item0.date.minute)
            Assert.assertEquals(50, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals(97, item0.childIcCardFare)
            Assert.assertEquals(100, item0.childTicketFare)
            // FIXME: 2017/12/18作成のデータでは"odpt:fromStation"の値が"odpt.Station:Tobu.Tojo.Uyama"になっているが、正しくは大山駅："odpt.Station:Tobu.Tojo.Oyama"のはず
            Assert.assertEquals("odpt.Station:Tobu.Tojo.Uyama", item0.fromStation?.id)
            Assert.assertEquals(195, item0.icCardFare)
            Assert.assertEquals("odpt.Operator:Tobu", item0.operator?.id)
            Assert.assertNull(item0.paymentMethodList)
            Assert.assertEquals(200, item0.ticketFare)
            Assert.assertNull(item0.ticketType)
            Assert.assertEquals("odpt.Station:Tobu.Tojo.Wakoshi", item0.toStation?.id)
            Assert.assertEquals("odpt.RailwayFare:Tobu.Tojo.Uyama.Tojo.Wakoshi", item0.sameAs?.id)
        }

        @Test
        fun 都営三田線三田発_都営浅草線馬込着() {
            // NOTE: 2017/12/18作成のデータでは"owl.sameAs"の値が仕様に従っていない：正しくは"odpt.RailwayFare:Toei.Mita.Mita.Toei.Asakusa.Magome"のはず
            val list: List<OdptRailwayFareResponse> = readTestData("RailwayFare_Toei_MitaMita_AsakusaMagome.json")
            Assert.assertEquals(1, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000030E6CB2", item0.odptId)

            // "2016-06-08T16:33:18+09:00"
            Assert.assertEquals(2016, item0.date.year)
            Assert.assertEquals(6, item0.date.month.value)
            Assert.assertEquals(8, item0.date.dayOfMonth)
            Assert.assertEquals(16, item0.date.hour)
            Assert.assertEquals(33, item0.date.minute)
            Assert.assertEquals(18, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals(108, item0.childIcCardFare)
            Assert.assertEquals(110, item0.childTicketFare)
            Assert.assertEquals("odpt.Station:Toei.Mita.Mita", item0.fromStation?.id)
            Assert.assertEquals(216, item0.icCardFare)
            Assert.assertEquals("odpt.Operator:Toei", item0.operator?.id)
            Assert.assertNull(item0.paymentMethodList)
            Assert.assertEquals(220, item0.ticketFare)
            Assert.assertNull(item0.ticketType)
            Assert.assertEquals("odpt.Station:Toei.Asakusa.Magome", item0.toStation?.id)
            Assert.assertEquals("odpt.RailwayFare:Toei.Mita.Mita.Asakusa.Magome", item0.sameAs?.id)
        }

        @Test
        fun 東京メトロ銀座線浅草発_東京メトロ銀座線京橋着() {
            val list: List<OdptRailwayFareResponse> = readTestData("RailwayFare_TokyoMetro_GinzaAsakusa_GinzaKyobashi.json")
            Assert.assertEquals(1, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000030CDD75", item0.odptId)

            // "2014-08-29T16:03:36+09:00"
            Assert.assertEquals(2014, item0.date.year)
            Assert.assertEquals(8, item0.date.month.value)
            Assert.assertEquals(29, item0.date.dayOfMonth)
            Assert.assertEquals(16, item0.date.hour)
            Assert.assertEquals(3, item0.date.minute)
            Assert.assertEquals(36, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals(97, item0.childIcCardFare)
            Assert.assertEquals(100, item0.childTicketFare)
            Assert.assertEquals("odpt.Station:TokyoMetro.Ginza.Asakusa", item0.fromStation?.id)
            Assert.assertEquals(195, item0.icCardFare)
            Assert.assertEquals("odpt.Operator:TokyoMetro", item0.operator?.id)
            Assert.assertNull(item0.paymentMethodList)
            Assert.assertEquals(200, item0.ticketFare)
            Assert.assertNull(item0.ticketType)
            Assert.assertEquals("odpt.Station:TokyoMetro.Ginza.Kyobashi", item0.toStation?.id)
            Assert.assertEquals("odpt.RailwayFare:TokyoMetro.Ginza.Asakusa.TokyoMetro.Ginza.Kyobashi", item0.sameAs?.id)
        }

        @Test
        fun ゆりかもめゆりかもめ線新橋発_ゆりかもめゆりかもめ線汐留着() {
            // NOTE: 2017/12/18作成のデータでは"owl.sameAs"の値が仕様に従っていない：正しくは"odpt.RailwayFare:Yurikamome.Yurikamome.Shimbashi.Yurikamome.Yurikamome.Shiodome"のはず
            val list: List<OdptRailwayFareResponse> = readTestData("RailwayFare_Yurikamome_YurikamomeShimbashi_YurikamomeShiodome.json")
            Assert.assertEquals(1, list.size)
            val item0 = list[0]
            Assert.assertEquals("urn:ucode:_00001C000000000000010000030E80B7", item0.odptId)

            // "2016-05-23T15:27:04+09:00"
            Assert.assertEquals(2016, item0.date.year)
            Assert.assertEquals(5, item0.date.month.value)
            Assert.assertEquals(23, item0.date.dayOfMonth)
            Assert.assertEquals(15, item0.date.hour)
            Assert.assertEquals(27, item0.date.minute)
            Assert.assertEquals(4, item0.date.second)
            Assert.assertEquals("+09:00", item0.date.zone.id)

            Assert.assertEquals(92, item0.childIcCardFare)
            Assert.assertEquals(100, item0.childTicketFare)
            Assert.assertEquals("odpt.Station:Yurikamome.Yurikamome.Shimbashi", item0.fromStation?.id)
            Assert.assertEquals(185, item0.icCardFare)
            Assert.assertEquals("odpt.Operator:Yurikamome", item0.operator?.id)
            Assert.assertNull(item0.paymentMethodList)
            Assert.assertEquals(190, item0.ticketFare)
            Assert.assertNull(item0.ticketType)
            Assert.assertEquals("odpt.Station:Yurikamome.Yurikamome.Shiodome", item0.toStation?.id)
            Assert.assertEquals("odpt.RailwayFare:Yurikamome.Yurikamome.Shimbashi.Yurikamome.Shiodome", item0.sameAs?.id)
        }
    }
}