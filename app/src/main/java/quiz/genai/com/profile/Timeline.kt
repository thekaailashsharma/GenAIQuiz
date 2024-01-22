package quiz.genai.com.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import quiz.genai.com.R
import quiz.genai.com.ui.theme.backGround
import quiz.genai.com.ui.theme.buttonBackground
import quiz.genai.com.utils.VerticalDashedDivider
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Random

data class Badges(
    val name: String,
    val color: Color,
    val image: Int,
    val date: Long, // Date in system time
)

val dummyBadges = listOf(
    Badges(
        name = "First Badge",
        color = Color(0xFFDCC1FF),
        image = R.drawable.badge1,
        date = 1667337600000
    ),
    Badges(
        name = "Second Badge",
        color = Color(0xFFF5F378),
        image = R.drawable.badge2,
        date = 1667683200000
    ),
    Badges(
        name = "Third Badge",
        color = Color(0xFFDCC1FF),
        image = R.drawable.badge3,
        date = 1668374400000
    ),
    Badges(
        name = "Fourth Badge",
        color = Color(0xFFF5F378),
        image = R.drawable.badge4,
        date = 1668720000000
    ),
    Badges(
        name = "Fifth Badge",
        color = Color(0xFFDCC1FF),
        image = R.drawable.badge1,
        date = 1669401600000
    ),
    Badges(
        name = "Sixth Badge",
        color = Color(0xFFF5F378),
        image = R.drawable.badge3,
        date = 1669913600000
    ),
    Badges(
        name = "Seventh Badge",
        color = Color(0xFFDCC1FF),
        image = R.drawable.badge4,
        date = 1670937600000
    ),
    Badges(
        name = "Eighth Badge",
        color = Color(0xFFF5F378),
        image = R.drawable.badge3,
        date = 1671449600000
    ),
    Badges(
        name = "Ninth Badge",
        color = Color(0xFFDCC1FF),
        image = R.drawable.badge2,
        date = 1664784000000
    ),
    Badges(
        name = "Tenth Badge",
        color = Color(0xFFF5F378),
        image = R.drawable.badge1,
        date = 1665296000000
    ),
    Badges(
        name = "Eleventh Badge",
        color = Color(0xFFDCC1FF),
        image = R.drawable.badge4,
        date = 1672665600000
    ),
    Badges(
        name = "Twelfth Badge",
        color = Color(0xFFF5F378),
        image = R.drawable.badge2,
        date = 1673270400000
    ),
    Badges(
        name = "Thirteenth Badge",
        color = Color(0xFFDCC1FF),
        image = R.drawable.badge2,
        date = 1672060800000
    ),
    Badges(
        name = "Fourteenth Badge",
        color = Color(0xFFF5F378),
        image = R.drawable.badge4,
        date = 1667337600000
    ),
    Badges(
        name = "Fifteenth Badge",
        color = Color(0xFFDCC1FF),
        image = R.drawable.badge2,
        date = 1667683200000
    ),
)

data class FormattedDate(
    val day: String,
    val month: Int,
    val year: String,
    val dayOfWeek: DayOfWeek,
    val date: Int,
)

data class GroupedBadges(
    val date: String, // Date in "dd MMM yyyy" format
    val badges: List<Badges>,
    val formattedDate: FormattedDate? = null,
    val millisTime: Long,
    val isCompleted: Boolean = kotlin.random.Random.nextBoolean(),
)

fun convertLocalDateToMillis(localDate: LocalDate): Long {
    val zoneId: ZoneId = ZoneId.systemDefault()
    val zonedDateTime: ZonedDateTime = localDate.atStartOfDay(zoneId)
    return zonedDateTime.toInstant().toEpochMilli()
}

val groupedBadges: List<GroupedBadges> = dummyBadges
    .groupBy { LocalDate.ofEpochDay(it.date / (24 * 60 * 60 * 1000)) }
    .map { entry ->
        val calendar = Calendar.getInstance().apply {
            timeInMillis = convertLocalDateToMillis(entry.key)
        }
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Month is zero-based
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dateNew = calendar.get(Calendar.DATE)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val formattedDate = FormattedDate(
            day = day.toString(),
            month = month,
            year = year.toString(),
            dayOfWeek = DayOfWeek.of(dayOfWeek),
            date = dateNew
        )
        val date = entry.key.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
        GroupedBadges(date, entry.value, formattedDate, convertLocalDateToMillis(entry.key))
    }
    .sortedByDescending { it.millisTime }


@Composable
fun TimeLine(groupedBadges: GroupedBadges) {
    GroupedBadgesCard(
        groupedBadges, buttonBorder = if (groupedBadges.isCompleted)
            Color(0xFFF5F378).copy(0.75f) else buttonBackground,
        isTranslated = groupedBadges.isCompleted,
    )

}