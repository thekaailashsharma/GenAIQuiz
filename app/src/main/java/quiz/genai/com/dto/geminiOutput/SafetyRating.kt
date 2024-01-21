package quiz.genai.com.dto.geminiOutput


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SafetyRating(
    @SerializedName("category")
    val category: String?,
    @SerializedName("probability")
    val probability: String?
)