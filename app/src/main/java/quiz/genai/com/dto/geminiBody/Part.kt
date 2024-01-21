package quiz.genai.com.dto.geminiBody


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Part(
    @SerializedName("text")
    val text: String?
)