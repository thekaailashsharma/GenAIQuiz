package quiz.genai.com.home

import java.time.DayOfWeek
import java.time.LocalDate

data class WeeklyGoals(
    val days: DailyData,
)

data class DailyData(
    val name: WeekDays,
    val recordedProgress: Long,
    val setProgress: Long,
)

enum class WeekDays(val day: String, val dayOfWeek: DayOfWeek) {
    Sunday("S", DayOfWeek.SUNDAY),
    Monday("M", DayOfWeek.MONDAY),
    Tuesday("T", DayOfWeek.TUESDAY),
    Wednesday("W", DayOfWeek.WEDNESDAY),
    Thursday("T", DayOfWeek.THURSDAY),
    Friday("F", DayOfWeek.FRIDAY),
    Saturday("S", DayOfWeek.SATURDAY),
}

fun isGoalAchieved(recordedProgress: Long, setProgress: Long): Boolean {
    return setProgress >= recordedProgress
}

fun isToday(dateInMillis: Long = System.currentTimeMillis()): Boolean {
    val date = LocalDate.ofEpochDay(dateInMillis / (24 * 60 * 60 * 1000))
    val today = LocalDate.now()
    return date.isEqual(today)
}

fun isDayOfWeekAfterToday(dayOfWeek: DayOfWeek = LocalDate.now().dayOfWeek): Boolean {
    val today = LocalDate.now().dayOfWeek
    return dayOfWeek > today
}

val dummyWeeklyGoals = listOf(
    WeeklyGoals(
        DailyData(
            WeekDays.Monday,
            100,
            150,
        )
    ),
    WeeklyGoals(
        DailyData(
            WeekDays.Tuesday,
            150,
            160,
        )
    ),
    WeeklyGoals(
        DailyData(
            WeekDays.Wednesday,
            150,
            160,
        )
    ),
    WeeklyGoals(
        DailyData(
            WeekDays.Thursday,
            150,
            100,
        )
    ),
    WeeklyGoals(
        DailyData(
            WeekDays.Friday,
            100,
            150,
        )
    ),
    WeeklyGoals(
        DailyData(
            WeekDays.Saturday,
            100,
            100,
        )
    ),
    WeeklyGoals(
        DailyData(
            WeekDays.Sunday,
            150,
            100,
        )
    ),
)


