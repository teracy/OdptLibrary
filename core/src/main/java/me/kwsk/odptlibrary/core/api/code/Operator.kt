package me.kwsk.odptlibrary.core.api.code

import me.kwsk.odptlibrary.core.api.common.*

// FIXME: 現在カタログからアクセスできる事業者のみなので、実装され次第反映する
const val OPERATOR_HND_TIAT: String = "odpt.Operator:HND-TIAT"
const val OPERATOR_JR_EAST: String = "odpt.Operator:JR-East"
const val OPERATOR_KEIKYU: String = "odpt.Operator:Keikyu"
const val OPERATOR_KEIO: String = "odpt.Operator:Keio"
const val OPERATOR_KEIO_BUS: String = "odpt.Operator:KeioBus"
const val OPERATOR_KEISEI: String = "odpt.Operator:Keisei"
const val OPERATOR_NAA: String = "odpt.Operator:NAA"
const val OPERATOR_ODAKYU: String = "odpt.Operator:Odakyu"
const val OPERATOR_ODAKYU_BUS: String = "odpt.Operator:OdakyuBus"
const val OPERATOR_SEIBU: String = "odpt.Operator:Seibu"
const val OPERATOR_SEIBU_BUS: String = "odpt.Operator:SeibuBus"
const val OPERATOR_TOBU: String = "odpt.Operator:Tobu"
const val OPERATOR_TOEI: String = "odpt.Operator:Toei"
const val OPERATOR_TOKYO_METRO: String = "odpt.Operator:TokyoMetro"
const val OPERATOR_TOKYU: String = "odpt.Operator:Tokyu"
const val OPERATOR_TWR: String = "odpt.Operator:TWR"
const val OPERATOR_YURIKAMOME: String = "odpt.Operator:Yurikamome"

interface Operator {
    /**
     * 事業者ID取得
     */
    fun getId(): String

    /**
     * OdptOperator型に変換する
     */
    fun toOdptOperator(): OdptOperator {
        return OdptOperator(getId())
    }
}

/**
 * 航空事業者ID（英語表記及び日本語表記はデータカタログ準拠）
 * この項目はAPIでは取れないためenumとして用意しておく
 *
 * @param code 事業者IDの"odpt.Operator:"以降
 */
enum class AirplaneOperator(var code: String) : Operator {
    NAA("NAA") {
        override fun getId(): String {
            return OPERATOR_NAA
        }
    },
    HND_TIAT("HND-TIAT") {
        override fun getId(): String {
            return OPERATOR_HND_TIAT
        }
    };

    companion object {
        /**
         * ID（e.g. "odpt.Operator:NAA"）を分解して任意の位置の項目から事業者IDを得る
         * @param id ID
         * @param index 評価する位置
         *
         */
        private fun find(id: String, index: Int): AirplaneOperator? {
            val name = id.split(Regex(":")).elementAtOrNull(1)
            val operatorName = name?.split(Regex("\\."))?.elementAtOrNull(index)
            return if (operatorName != null) find(operatorName) else null
        }

        /**
         * 事業者ID"odpt.Operator:"以降のみで検索（e.g. "odpt.Operator:NAA"->"NAA"）
         */
        private fun find(operatorName: String): AirplaneOperator? {
            return values().find { it.code == operatorName }
        }

        /**
         * 事業者IDで検索（e.g. "odpt.Operator:NAA"）
         */
        fun find(operator: OdptOperator): AirplaneOperator? {
            return values().find { operator.id == it.getId() }
        }
    }
}

/**
 * バス事業者ID（英語表記及び日本語表記はデータカタログ準拠）
 * この項目はAPIでは取れないためenumとして用意しておく
 *
 * @param code 事業者IDの"odpt.Operator:"以降
 */
enum class BusOperator(var code: String) : Operator {
    // FIXME: 現在カタログからアクセスできる事業者のみなので、実装され次第反映する
    KEIO_BUS("KeioBus") {
        override fun getId(): String {
            return OPERATOR_KEIO_BUS
        }
    },
    ODAKYU_BUS("OdakyuBus") {
        override fun getId(): String {
            return OPERATOR_ODAKYU_BUS
        }
    },
    SEIBU_BUS("SeibuBus") {
        override fun getId(): String {
            return OPERATOR_SEIBU_BUS
        }
    },
    TOEI("Toei") {
        override fun getId(): String {
            return OPERATOR_TOEI
        }
    };

    companion object {
        /**
         * ID（e.g. "odpt.Operator:OdakyuBus"）を分解して任意の位置の項目から事業者IDを得る
         * @param id ID
         * @param index 評価する位置
         *
         */
        private fun find(id: String, index: Int): BusOperator? {
            val name = id.split(Regex(":")).elementAtOrNull(1)
            val operatorName = name?.split(Regex("\\."))?.elementAtOrNull(index)
            return if (operatorName != null) find(operatorName) else null
        }

        /**
         * 事業者ID"odpt.Operator:"以降のみで検索（e.g. "odpt.Operator:OdakyuBus"->"OdakyuBus"）
         */
        private fun find(operatorName: String): BusOperator? {
            return values().find { it.code == operatorName }
        }

        /**
         * 事業者IDで検索（e.g. "odpt.Operator:OdakyuBus"）
         */
        fun find(operator: OdptOperator): BusOperator? {
            return values().find { operator.id == it.getId() }
        }

        /**
         * バス路線IDで検索（e.g. "odpt.BusroutePattern:OdakyuBus.Kichi06.10201.1"）
         */
        fun find(busRoutePattern: OdptBusRoutePattern): BusOperator? {
            return find(busRoutePattern.id, 0)
        }

        /**
         * バス時刻表IDで検索（e.g. "odpt.BusTimetable:OdakyuBus.Kichi06.10201.1.1100.100000143.Specific.OdakyuBus.Kichijouji.Weekday01"）
         */
        fun find(busTimetable: OdptBusTimetable): BusOperator? {
            return find(busTimetable.id, 0)
        }
    }
}

/**
 * 鉄道事業者ID（英語表記及び日本語表記はデータカタログ準拠）
 * この項目はAPIでは取れないためenumとして用意しておく
 *
 * @param code 事業者IDの"odpt.Operator:"以降
 */
enum class RailwayOperator(var code: String) : Operator {
    JR_EAST("JR-East") {
        override fun getId(): String {
            return OPERATOR_JR_EAST
        }
    },
    KEIKYU("Keikyu") {
        override fun getId(): String {
            return OPERATOR_KEIKYU
        }
    },
    KEIO("Keio") {
        override fun getId(): String {
            return OPERATOR_KEIO
        }
    },
    KEISEI("Keisei") {
        override fun getId(): String {
            return OPERATOR_KEISEI
        }
    },
    ODAKYU("Odakyu") {
        override fun getId(): String {
            return OPERATOR_ODAKYU
        }
    },
    SEIBU("Seibu") {
        override fun getId(): String {
            return OPERATOR_SEIBU
        }
    },
    TOBU("Tobu") {
        override fun getId(): String {
            return OPERATOR_TOBU
        }
    },
    TOEI("Toei") {
        override fun getId(): String {
            return OPERATOR_TOEI
        }
    },
    TOKYO_METRO("TokyoMetro") {
        override fun getId(): String {
            return OPERATOR_TOKYO_METRO
        }
    },
    TOKYU("Tokyu") {
        override fun getId(): String {
            return OPERATOR_TOKYU
        }
    },
    TWR("TWR") {
        override fun getId(): String {
            return OPERATOR_TWR
        }
    },
    YURIKAMOME("Yurikamome") {
        override fun getId(): String {
            return OPERATOR_YURIKAMOME
        }
    };

    companion object {
        /**
         * ID（e.g. "odpt.Operator:TokyoMetro"）を分解して任意の位置の項目から事業者IDを得る
         * @param id ID
         * @param index 評価する位置
         *
         */
        private fun find(id: String, index: Int): RailwayOperator? {
            val name = id.split(Regex(":")).elementAtOrNull(1)
            val operatorName = name?.split(Regex("\\."))?.elementAtOrNull(index)
            return find(operatorName)
        }

        /**
         * 事業者ID"odpt.Operator:"以降のみで検索（e.g. "odpt.Operator:TokyoMetro"->"TokyoMetro"）
         */
        private fun find(operatorName: String?): RailwayOperator? {
            return values().find { it.code == operatorName }
        }

        /**
         * 事業者IDで検索（e.g. "odpt.Operator:TokyoMetro"）
         */
        fun find(operator: OdptOperator): RailwayOperator? {
            return values().find { operator.id == it.getId() }
        }

        /**
         * 駅乗降人員情報IDで検索（e.g. "odpt:PassengerSurvey:TokyoMetro.Tokyo.2013"）
         */
        fun find(passengerSurvey: OdptPassengerSurvey): RailwayOperator? {
            return find(passengerSurvey.id, 0)
        }

        /**
         * 方面IDで検索（e.g. "odpt.RailDirection:TokyoMetro.Wakoshi"）
         */
        fun find(railDirection: OdptRailDirection): RailwayOperator? {
            return find(railDirection.id, 0)
        }

        /**
         * 鉄道路線IDで検索（e.g. "odpt.Railway:TokyoMetro.Yurakucho"）
         */
        fun find(railway: OdptRailway): RailwayOperator? {
            return find(railway.id, 0)
        }

        /**
         * 運賃情報IDから出発駅の事業者IDを検索（e.g. "odpt.RailwayFare:TokyoMetro.Ginza.Asakusa.TokyoMetro.Ginza.Kyobashi"）
         */
        fun findDepartureOperator(railwayFare: OdptRailwayFare): RailwayOperator? {
            val railwayFareName = railwayFare.id.split(Regex(":")).elementAtOrNull(1)
            val operatorName = railwayFareName?.split(Regex("\\."))?.elementAtOrNull(0)
            return find(operatorName)
        }

        /**
         * 運賃情報IDから到着駅の事業者IDを検索（e.g. "odpt.RailwayFare:TokyoMetro.Ginza.Asakusa.TokyoMetro.Ginza.Kyobashi"）
         * @return 「到着駅の会社名」、「到着駅の会社名」が省略されている場合は「出発駅の会社名」
         */
        fun findArrivalOperator(railwayFare: OdptRailwayFare): RailwayOperator? {
            /**
             * NOTE:
             * 取得可能な事業者は{京急電鉄・西武鉄道・都営・東武鉄道・東急電鉄・東京メトロ・臨海高速鉄道・ゆりかもめ}であるが、
             * このうち2017/12/20現在取得可能なのは{都営・東武鉄道・東京メトロ・ゆりかもめ}であり、
             * 更にそのうちの{都営・東武鉄道・ゆりかもめ}はフォーマットが仕様に則っていない
             * 仕様：「odpt.RailwayFare:出発駅の会社名.出発駅の路線名.出発駅名.到着駅の会社名.到着駅の路線名.到着駅名」
             * これら事業者のフォーマット：「odpt.RailwayFare:出発駅の会社名.出発駅の路線名.出発駅名.到着駅の路線名.到着駅名」
             * 従って、仕様外のこれら事業者でも取得できるようにしている
             */
            // 「odpt.RailwayFare:」より後ろの部分抽出
            val railwayFareName = railwayFare.id.split(Regex(":")).elementAtOrNull(1)
            val split = railwayFareName?.split(Regex("\\."))
            val operatorName = when (split?.size ?: 0) {
                6 -> {
                    // 仕様に則っている場合は「到着駅の会社名」を利用
                    split?.elementAtOrNull(3)
                }
                5 -> {
                    // 「到着駅の会社名」が省略されている場合は「出発駅の会社名」を利用
                    split?.elementAtOrNull(0)
                }
                else -> {
                    return null
                }
            }
            return find(operatorName)
        }

        /**
         * 駅IDで検索（e.g. "odpt.Station:Tobu.TobuSkytree.Asakusa"）
         */
        fun find(station: OdptStation): RailwayOperator? {
            return find(station.id, 0)
        }

        /**
         * 駅時刻表IDで検索（e.g. "odpt.StationTimetable:TokyoMetro.Hanzomon.Oshiage.Shibuya.Saturday"）
         */
        fun find(stationTimetable: OdptStationTimetable): RailwayOperator? {
            return find(stationTimetable.id, 0)
        }

        /**
         * 列車IDで検索（e.g. "odpt.Train:JR-East.Utsunomiya.565M"）
         */
        fun find(train: OdptTrain): RailwayOperator? {
            return find(train.id, 0)
        }

        /**
         * 車両所属会社IDで検索（e.g. "odpt.TrainOwner:TokyoMetro"）
         */
        fun find(trainOwner: OdptTrainOwner): RailwayOperator? {
            val operatorName = trainOwner.id.split(Regex(":")).elementAtOrNull(1)
            return find(operatorName)
        }

        /**
         * 列車時刻表IDで検索（e.g. "odpt.TrainTimetable:TokyoMetro.Tozai.A2063S.Weekdays.Weekday"）
         */
        fun find(trainTimetable: OdptTrainTimetable): RailwayOperator? {
            return find(trainTimetable.id, 0)
        }
    }
}