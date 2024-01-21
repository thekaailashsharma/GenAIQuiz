package quiz.genai.com.dto.geminiOutput


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Candidate(
    @SerializedName("content")
    val content: Content?,
    @SerializedName("finishReason")
    val finishReason: String?,
    @SerializedName("index")
    val index: Int?,
    @SerializedName("safetyRatings")
    val safetyRatings: List<SafetyRating?>?
)