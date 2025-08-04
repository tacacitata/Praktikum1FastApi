package project.android.project1fastapi.network

import retrofit2.Call
import retrofit2.http.*
import project.android.project1fastapi.model.Mahasiswa

interface ApiService {
    @GET("mahasiswa/")
    fun getMahasiswa(): Call<List<Mahasiswa>>

    @POST("mahasiswa/")
    fun tambahMahasiswa(@Body mahasiswa: Mahasiswa): Call<Map<String, String>>
}