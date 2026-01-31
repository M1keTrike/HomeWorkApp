package com.homeworkapp.core.network

import com.homeworkapp.features.homework.data.datasources.remote.model.WebhookResponse
import retrofit2.http.POST

interface HomeworkApi {

    @POST("webhook/ba236f18-6721-46b2-9238-825b282afa22")
    suspend fun getWebhookTasks(): WebhookResponse
}