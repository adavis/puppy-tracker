package info.adavis.puppytracker

import retrofit2.http.GET

const val BASE_URL = "https://random.dog"

interface RandomPupApi {

    @GET("woof.json")
    suspend fun randomPup(): RandomPup
}