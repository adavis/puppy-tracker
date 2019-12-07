package info.adavis.puppytracker

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RandomPup(@Json(name = "url") val imageUrl: String)