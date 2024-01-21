package quiz.genai.com.dto.geminiOutput


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GeminiOutput(
    @SerializedName("candidates")
    val candidates: List<Candidate>?,
    @SerializedName("promptFeedback")
    val promptFeedback: PromptFeedback?
)