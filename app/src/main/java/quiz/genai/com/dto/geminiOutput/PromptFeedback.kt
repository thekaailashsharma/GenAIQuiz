package quiz.genai.com.dto.geminiOutput


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PromptFeedback(
    @SerializedName("safetyRatings")
    val safetyRatings: List<SafetyRating>?
)