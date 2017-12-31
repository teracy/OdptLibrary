package me.kwsk.odptlibrary.core.api.code

import android.support.test.runner.AndroidJUnit4
import me.kwsk.odptlibrary.core.api.common.*
import org.junit.Assert
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * 事業者IDのテスト
 * Created by teracy on 2017/12/12.
 */
@RunWith(Enclosed::class)
class RailwayOperatorTest {
    @RunWith(AndroidJUnit4::class)
    class find_事業者ID {
        @Test
        fun 東京メトロ() {
            val operator = OdptOperator("odpt.Operator:TokyoMetro")
            Assert.assertEquals(RailwayOperator.TOKYO_METRO, RailwayOperator.find(operator))
        }
    }

    @RunWith(AndroidJUnit4::class)
    class find_駅別乗降人員ID {
        @Test
        fun 東京メトロ_2013年() {
            val passengerSurvey = OdptPassengerSurvey("odpt.PassengerSurvey:TokyoMetro.Tokyo.2013")
            Assert.assertEquals(RailwayOperator.TOKYO_METRO, RailwayOperator.find(passengerSurvey))
        }
    }

    @RunWith(AndroidJUnit4::class)
    class find_方面ID {
        @Test
        fun 東京メトロ_和光市方面() {
            val railDirection = OdptRailDirection("odpt.RailDirection:TokyoMetro.Wakoshi")
            Assert.assertEquals(RailwayOperator.TOKYO_METRO, RailwayOperator.find(railDirection))
        }
    }

    @RunWith(AndroidJUnit4::class)
    class find_路線ID {
        @Test
        fun 東京メトロ_有楽町線() {
            val railway = OdptRailway("odpt.Railway:TokyoMetro.Yurakucho")
            Assert.assertEquals(RailwayOperator.TOKYO_METRO, RailwayOperator.find(railway))
        }
    }

    @RunWith(Enclosed::class)
    class findDepartureOperator {
        @RunWith(AndroidJUnit4::class)
        class 仕様に則ったデータ {
            @Test
            fun 東京メトロ_銀座線_浅草発_東京メトロ_銀座線_京橋着() {
                val railway = OdptRailwayFare("odpt.RailwayFare:TokyoMetro.Ginza.Asakusa.TokyoMetro.Ginza.Kyobashi")
                Assert.assertEquals(RailwayOperator.TOKYO_METRO, RailwayOperator.findDepartureOperator(railway))
            }
        }

        @RunWith(AndroidJUnit4::class)
        class 仕様外のデータ {
            @Test
            fun 東武_東上線_大山発_東武_東上線_和光市着() {
                val railway = OdptRailwayFare("odpt.RailwayFare:Tobu.Tojo.Uyama.Tojo.Wakoshi")
                Assert.assertEquals(RailwayOperator.TOBU, RailwayOperator.findDepartureOperator(railway))
            }

            @Test
            fun 都営_三田線_三田発_都営_浅草線_馬込着() {
                val railway = OdptRailwayFare("odpt.RailwayFare:Toei.Mita.Mita.Asakusa.Magome")
                Assert.assertEquals(RailwayOperator.TOEI, RailwayOperator.findDepartureOperator(railway))
            }

            @Test
            fun ゆりかもめ_ゆりかもめ線_新橋発_ゆりかもめ_ゆりかもめ線_汐留着() {
                val railway = OdptRailwayFare("odpt.RailwayFare:Yurikamome.Yurikamome.Shimbashi.Yurikamome.Shiodome")
                Assert.assertEquals(RailwayOperator.YURIKAMOME, RailwayOperator.findDepartureOperator(railway))
            }
        }
    }

    @RunWith(Enclosed::class)
    class findArrivalOperator {
        @RunWith(AndroidJUnit4::class)
        class 仕様に則ったデータ {
            @Test
            fun 東京メトロ_銀座線_浅草発_東京メトロ_銀座線_京橋着() {
                val railway = OdptRailwayFare("odpt.RailwayFare:TokyoMetro.Ginza.Asakusa.TokyoMetro.Ginza.Kyobashi")
                Assert.assertEquals(RailwayOperator.TOKYO_METRO, RailwayOperator.findArrivalOperator(railway))
            }
        }

        @RunWith(AndroidJUnit4::class)
        class 仕様外のデータ {
            @Test
            fun 東武_東上線_大山発_東武_東上線_和光市着() {
                val railway = OdptRailwayFare("odpt.RailwayFare:Tobu.Tojo.Uyama.Tojo.Wakoshi")
                Assert.assertEquals(RailwayOperator.TOBU, RailwayOperator.findArrivalOperator(railway))
            }

            @Test
            fun 都営_三田線_三田発_都営_浅草線_馬込着() {
                val railway = OdptRailwayFare("odpt.RailwayFare:Toei.Mita.Mita.Asakusa.Magome")
                Assert.assertEquals(RailwayOperator.TOEI, RailwayOperator.findArrivalOperator(railway))
            }

            @Test
            fun ゆりかもめ_ゆりかもめ線_新橋発_ゆりかもめ_ゆりかもめ線_汐留着() {
                val railway = OdptRailwayFare("odpt.RailwayFare:Yurikamome.Yurikamome.Shimbashi.Yurikamome.Shiodome")
                Assert.assertEquals(RailwayOperator.YURIKAMOME, RailwayOperator.findArrivalOperator(railway))
            }
        }
    }

    @RunWith(AndroidJUnit4::class)
    class find_駅ID {
        @Test
        fun 東武_スカイツリーライン_浅草駅() {
            val station = OdptStation("odpt.Station:Tobu.TobuSkytree.Asakusa")
            Assert.assertEquals(RailwayOperator.TOBU, RailwayOperator.find(station))
        }
    }

    @RunWith(AndroidJUnit4::class)
    class find_駅時刻表ID {
        @Test
        fun 東京メトロ_半蔵門線_押上駅_渋谷方面_土曜日() {
            val stationTimetable = OdptStationTimetable("odpt.StationTimetable:TokyoMetro.Hanzomon.Oshiage.Shibuya.Saturday")
            Assert.assertEquals(RailwayOperator.TOKYO_METRO, RailwayOperator.find(stationTimetable))
        }
    }

    @RunWith(AndroidJUnit4::class)
    class find_列車ID {
        @Test
        fun JR東日本_宇都宮線_565M列車() {
            val stationTimetable = OdptTrain("odpt.Train:JR-East.Utsunomiya.565M")
            Assert.assertEquals(RailwayOperator.JR_EAST, RailwayOperator.find(stationTimetable))
        }
    }

    @RunWith(AndroidJUnit4::class)
    class find_車両所属会社ID {
        @Test
        fun 東京メトロ() {
            val trainOwner = OdptTrainOwner("odpt.TrainOwner:TokyoMetro")
            Assert.assertEquals(RailwayOperator.TOKYO_METRO, RailwayOperator.find(trainOwner))
        }
    }

    @RunWith(AndroidJUnit4::class)
    class find_列車時刻表ID {
        @Test
        fun 東京メトロ_東西線_A2063S列車_平日() {
            val trainTimetable = OdptTrainTimetable("odpt.TrainTimetable:TokyoMetro.Tozai.A2063S.Weekdays.Weekday")
            Assert.assertEquals(RailwayOperator.TOKYO_METRO, RailwayOperator.find(trainTimetable))
        }
    }
}
