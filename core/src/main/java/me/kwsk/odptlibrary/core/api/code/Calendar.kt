package me.kwsk.odptlibrary.core.api.code

import me.kwsk.odptlibrary.core.api.common.OdptCalendar

// NOTE: あくまで一般的な「暦」であり、事業者固有のものは保持しない
/**
 * 運行日付・曜日のうち、曜日や"Weekday"といった固定値
 *
 * @param odptCalendar OdptCalendar
 * @param resourceId 運行日付・曜日名リソース
 */
enum class Calendar(val id: String) {
    MONDAY("odpt.Calendar:Monday"),
    TUESDAY("odpt.Calendar:Tuesday"),
    WEDNESDAY("odpt.Calendar:Wednesday"),
    THURSDAY("odpt.Calendar:Thursday"),
    FRIDAY("odpt.Calendar:Friday"),
    SATURDAY("odpt.Calendar:Saturday"),
    SUNDAY("odpt.Calendar:Sunday"),
    WEEKDAY("odpt.Calendar:Weekday"),
    HOLIDAY("odpt.Calendar:Holiday"),
    SATURDAY_HOLIDAY("odpt.Calendar:SaturdayHoliday");

    /**
     * OdptCalendar型に変換する
     */
    fun toOdptCalendar(): OdptCalendar {
        return OdptCalendar(id)
    }

    companion object {
        fun find(odptCalendar: OdptCalendar): Calendar? {
            val filter = values().filter { it.toOdptCalendar() == odptCalendar }
            return if (filter.isEmpty()) null else filter.get(0)
        }
    }
}
