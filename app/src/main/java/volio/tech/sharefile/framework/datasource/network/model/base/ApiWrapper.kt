package volio.tech.sharefile.framework.datasource.network.model.base

import com.google.gson.annotations.SerializedName

data class ApiWrapper<T>(
    @SerializedName("code")
    val code: Int,

    @SerializedName("msg")
    val message: String,

    @SerializedName("data")
    val data: T
)