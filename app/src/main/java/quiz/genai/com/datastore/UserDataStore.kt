package quiz.genai.com.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.datastore: DataStore<Preferences> by preferencesDataStore("pref")

class UserDatastore(private val context: Context) {
    companion object {
        val dailyGoal = longPreferencesKey("dailyGoal")
    }

    val getDailyGoal: Flow<Long> = context.datastore.data.map {
        it[dailyGoal] ?: 0L
    }

    suspend fun saveGoal(number: Long) {
        context.datastore.edit {
            it[dailyGoal] = number
        }
    }

}