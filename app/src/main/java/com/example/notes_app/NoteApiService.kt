package com.example.notes_app

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NoteApiService {
    @GET("notes")
    suspend fun getNotes(): List<Note>

    @POST("notes")
    suspend fun addNote(@Body note: Note): Note
}
