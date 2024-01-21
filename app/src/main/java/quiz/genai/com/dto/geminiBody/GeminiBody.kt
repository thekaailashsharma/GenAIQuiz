package quiz.genai.com.dto.geminiBody


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GeminiBody(
    @SerializedName("contents")
    val contents: List<Content?>?
)