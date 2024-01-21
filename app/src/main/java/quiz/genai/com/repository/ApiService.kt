package quiz.genai.com.repository

import quiz.genai.com.dto.geminiBody.GeminiBody
import quiz.genai.com.dto.geminiOutput.GeminiOutput

interface ApiService {
    suspend fun getQuiz(body: GeminiBody): GeminiOutput
}