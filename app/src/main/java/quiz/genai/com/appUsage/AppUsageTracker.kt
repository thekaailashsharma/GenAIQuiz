package quiz.genai.com.appUsage

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TimeTracker(private val context: Context) : LifecycleObserver {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("TimeTrackerPrefs", Context.MODE_PRIVATE)

    private var startTime: Long = 0

    private val _totalTimeSpentFlow = MutableStateFlow(getTotalTimeSpent())
    val totalTimeSpentFlow: Flow<Long> get() = _totalTimeSpentFlow

    init {
        // Initialize the start time
        startTime = sharedPreferences.getLong(KEY_START_TIME, System.currentTimeMillis())
    }

    // Start tracking time
    fun startTracking() {
        startTime = System.currentTimeMillis()
        sharedPreferences.edit {
            putLong(KEY_START_TIME, startTime)
        }
    }

    // Stop tracking time
    fun stopTracking() {
        // Calculate the elapsed time
        val elapsedTime = System.currentTimeMillis() - startTime

        // Add the elapsed time to the total time spent
        val totalTimeSpent = sharedPreferences.getLong(KEY_TOTAL_TIME_SPENT, 0) + elapsedTime

        // Save the updated total time spent
        sharedPreferences.edit {
            putLong(KEY_TOTAL_TIME_SPENT, totalTimeSpent)
        }

        // Update the Flow with the new total time spent
        _totalTimeSpentFlow.value = totalTimeSpent
    }

    // Get the total time spent
    fun getTotalTimeSpent(): Long {
        return sharedPreferences.getLong(KEY_TOTAL_TIME_SPENT, 0)
    }

    // Reset total time spent
    fun resetTime() {
        sharedPreferences.edit {
            putLong(KEY_TOTAL_TIME_SPENT, 0)
        }

        // Update the Flow with the reset value
        _totalTimeSpentFlow.value = 0
    }

    // Attach the TimeTracker to a lifecycle owner (e.g., activity or fragment)
    fun attachToLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    // Detach the TimeTracker from the lifecycle owner
    fun detachFromLifecycle(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onBackground() {
        stopTracking()
    }

    companion object {
        private const val KEY_START_TIME = "start_time"
        private const val KEY_TOTAL_TIME_SPENT = "total_time_spent"
    }
}


