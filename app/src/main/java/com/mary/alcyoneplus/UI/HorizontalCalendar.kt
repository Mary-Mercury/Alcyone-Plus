import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mary.alcyoneplus.R
import com.mary.alcyoneplus.UI.MainViewModel
import com.mary.compose.AlcyonePlusTheme
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream
import java.text.SimpleDateFormat
import java.time.temporal.WeekFields
import java.util.Locale.ENGLISH

@Preview(showBackground = true)
@Composable
fun CalendarAppPreview() {
    AlcyonePlusTheme {
        CalendarApp(
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun CalendarApp(modifier: Modifier = Modifier) {
    val dataSource = CalendarDataSource()
    var calendarUiModel by remember { mutableStateOf(dataSource.getData(lastSelectedDate = dataSource.today)) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            data = calendarUiModel,
            onPrevClickListener = { startDate ->
                val finalStartDate = startDate.minusDays(1)
                calendarUiModel = dataSource.getData(startDate = finalStartDate, lastSelectedDate = calendarUiModel.selectedDate.date)
            },
            onNextClickListener = { endDate ->
                val finalStartDate = endDate.plusDays(2)
                calendarUiModel = dataSource.getData(startDate = finalStartDate, lastSelectedDate = calendarUiModel.selectedDate.date)
            }
        )
        Content(data = calendarUiModel, onDateClickListener = { date ->
            calendarUiModel = calendarUiModel.copy(
                selectedDate = date,
                visibleDates = calendarUiModel.visibleDates.map {
                    it.copy(
                        isSelected = it.date.isEqual(date.date)
                    )
                }
            )
        })
    }
}

@Composable
fun Header(
    data: CalendarUiModel,
    onPrevClickListener: (LocalDate) -> Unit,
    onNextClickListener: (LocalDate) -> Unit,) {
    Row {

        Text(
            text = if (data.selectedDate.isToday) {
                stringResource(R.string.Today)
            } else {
                data.selectedDate.date.format(
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                )
            },
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        IconButton(onClick = {
            onPrevClickListener(data.startDate.date)
        }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.Back)
            )
        }
        IconButton(onClick = {
            onNextClickListener(data.endDate.date)
        }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = stringResource(R.string.Next)
            )
        }
    }
}

@Composable
fun Content(
    data: CalendarUiModel,
    onDateClickListener: (CalendarUiModel.Date) -> Unit,
) {
    LazyRow {
        items(items = data.visibleDates) { date ->
            ContentItem(
                date = date,
                onDateClickListener
            )
        }
    }
}

@Composable
fun ContentItem(
    date: CalendarUiModel.Date,
    onClickListener: (CalendarUiModel.Date) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .clickable {
                onClickListener(date)
            }
        ,
        colors = CardDefaults.cardColors(
            containerColor = if (date.isSelected) {

                val switchState by viewModel.switchState.collectAsState()

                if (switchState) {
                    viewModel.updateDay(date.getDayInfo())
                    viewModel.updateWeek(date.getWeekInfoInverted())
                    viewModel.filterData(date.getDayInfo(), date.getWeekInfoInverted())
                } else {
                    viewModel.updateDay(date.getDayInfo())
                    viewModel.updateWeek(date.getWeekInfo())
                    viewModel.filterData(date.getDayInfo(), date.getWeekInfo())
                }
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondary
            }
        ),
    ) {
        Column(
            modifier = Modifier
                .width(46.dp)
                .height(55.dp)
                .padding(8.dp)
        ) {
            Text(
                text = date.day,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = date.date.dayOfMonth.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

data class CalendarUiModel(
    val selectedDate: Date,
    val visibleDates: List<Date>,
) {
    val startDate: Date = visibleDates.first()
    val endDate: Date = visibleDates.last()

    data class Date(
        val date: LocalDate,
        val isSelected: Boolean,
        val isToday: Boolean,
    ) {
        val day: String = date.format(DateTimeFormatter.ofPattern("E"))

        fun getDayInfo(): String {
            val datee = java.util.Date.from(date.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant())
            val dayOfWeek = SimpleDateFormat("EEEE", ENGLISH).format(datee)
            return dayOfWeek.uppercase()
        }
        fun getWeekInfoInverted(): String {
            val weekFields = WeekFields.ISO
            val weekInfo = date.get(weekFields.weekOfWeekBasedYear())
            return if (weekInfo % 2 == 1) { "четная" } else { "нечетная" }
        }
        fun getWeekInfo(): String {
            val weekFields = WeekFields.ISO
            val weekInfo = date.get(weekFields.weekOfWeekBasedYear())
            return if (weekInfo % 2 == 1) { "нечетная" } else { "четная" }
        }
    }
}

class CalendarDataSource {

    val today: LocalDate
        get() {
            return LocalDate.now()
        }
    fun getData(startDate: LocalDate = today, lastSelectedDate: LocalDate): CalendarUiModel {
        val firstDayOfWeek = startDate.with(DayOfWeek.MONDAY)
        val endDayOfWeek = firstDayOfWeek.plusDays(7)
        val visibleDates = getDatesBetween(firstDayOfWeek, endDayOfWeek)
        return toUiModel(visibleDates, lastSelectedDate)
    }
    private fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        val numOfDays = ChronoUnit.DAYS.between(startDate, endDate)
        return Stream.iterate(startDate) { date ->
            date.plusDays( 1)
        }
            .limit(numOfDays)
            .collect(Collectors.toList())
    }
    private fun toUiModel(
        dateList: List<LocalDate>,
        lastSelectedDate: LocalDate
    ): CalendarUiModel {
        return CalendarUiModel(
            selectedDate = toItemUiModel(lastSelectedDate, true),
            visibleDates = dateList.map {
                toItemUiModel(it, it.isEqual(lastSelectedDate))
            },
        )
    }
    private fun toItemUiModel(date: LocalDate, isSelectedDate: Boolean) = CalendarUiModel.Date(
        date = date,
        isSelected = isSelectedDate,
        isToday = date.isEqual(today)
    )
}
