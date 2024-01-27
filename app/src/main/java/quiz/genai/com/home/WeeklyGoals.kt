package quiz.genai.com.home

import androidx.compose.ui.graphics.Color
import quiz.genai.com.R
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

val discover = listOf(
    "Discover", "Saved", "Applied", "Closed", "Viewed", "Selected"
)

val colors = listOf(
    Color(0xFF5424FD),
    Color(0xFFF5001E),
    Color(0xFFFCC636),

)

data class AllJobs(
    val title: String,
    val company: String,
    val location: String,
    val salary: String,
    val image: Int,
    val description: String,
    val isSaved: Boolean = false,
    val isApplied: Boolean = false,
    val isClosed: Boolean = false,
    val isViewed: Boolean = false,
    val isSelected: Boolean = false,
    val color: Color = colors.random(),
    val textColor: Color = if (color == colors[2]) Color.Black else Color.White,
)

val jobsList = listOf(
    AllJobs(
        "Android Developer",
        "Google",
        "Bangalore",
        "₹ 10,00,000",
        R.drawable.google,
        "Android Developers are responsible for developing applications for mobile " +
                "devices that run on the Android operating system. An Android developer must pay special attention to the application’s compatibility with multiple versions of Android and device types. They must also have a strong understanding of the patterns and practices that revolve around such a platform.",
    ),
    AllJobs(
        "Project Manager",
        "Slack",
        "Bangalore",
        "₹ 15,00,000",
        R.drawable.slack,
        "Project managers are responsible for planning and overseeing projects to ensure they are completed in a timely fashion and within budget. Project managers plan and designate project resources, prepare budgets, monitor progress, and keep stakeholders informed the entire way.",
    ),
    AllJobs(
        "UX Designer",
        "Microsoft",
        "Bangalore",
        "₹ 12,00,000",
        R.drawable.microsoft,
        "UX designers are primarily concerned with how the product feels. A given design problem has no single right answer. UX designers explore many different approaches to solving a specific user problem. The broad responsibility of a UX designer is to ensure that the product logically flows from one step to the next.",
    ),
    AllJobs(
        "Product Manager",
        "Tesla",
        "Bangalore",
        "₹ 20,00,000",
        R.drawable.tesla,
        "Product managers are responsible for guiding the success of a product and leading the cross-functional team that is responsible for improving it. It is an important organizational role — especially in technology companies — that sets the strategy, roadmap, and feature definition for a product or product line.",
    ),
    AllJobs(
        "Data Scientist",
        "Motorola",
        "Bangalore",
        "₹ 18,00,000",
        R.drawable.motorola,
        "Data scientists are responsible for discovering insights from massive amounts of structured and unstructured data to help shape or meet specific business needs and goals. The data scientist role involves a blend of skills in three major areas: business acumen, computer technology/programming, and statistics/math.",
    ),
    AllJobs(
        "Software Engineer",
        "Youtube",
        "Bangalore",
        "₹ 25,00,000",
        R.drawable.youtube,
        "Software engineers are responsible for developing, testing, deploying, and revamping computer programs. They are responsible for determining how a program will function by breaking down each step of the process into a logical series. The software engineer then converts the design into a sequence of instructions that a computer can follow.",
    ),
)


