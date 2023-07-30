package com.devseok.gas.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author Ha Jin Seok
 * @created 2023-07-25
 * @desc
 */
// TmCoordinatesResponse.kt
data class TmCoordinatesResponse(
    @SerializedName("documents")
    val documents: List<Document>?,
    @SerializedName("meta")
    val meta: Meta?
)

// Document.kt
data class Document(
    @SerializedName("x")
    val x: Double?,
    @SerializedName("y")
    val y: Double?
)

// Meta.kt
data class Meta(
    @SerializedName("total_count")
    val totalCount: Int?
)