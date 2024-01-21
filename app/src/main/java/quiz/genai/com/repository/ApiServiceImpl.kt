package quiz.genai.com.repository

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import quiz.genai.com.dto.geminiBody.GeminiBody
import quiz.genai.com.dto.geminiOutput.Candidate
import quiz.genai.com.dto.geminiOutput.GeminiOutput

class ApiServiceImpl(private val client: HttpClient): ApiService {
    override suspend fun getQuiz(body: GeminiBody): GeminiOutput {
        return try {
            client.post {
                url(ApiRoutes.BASE_URL)
                setBody(body)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }.body()
        } catch (e: Exception) {
            Log.i("ApiException", e.message.toString())
            return GeminiOutput(
                candidates = null,
                promptFeedback = null
            )
        }

    }

}