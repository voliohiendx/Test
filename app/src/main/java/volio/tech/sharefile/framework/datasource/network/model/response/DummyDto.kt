package volio.tech.sharefile.framework.datasource.network.model.response

import com.google.gson.annotations.SerializedName

data class DummyDto(

    @SerializedName(ID)
    val id: Int,

    @SerializedName(TITLE)
    val title: String,

    @SerializedName(BODY)
    val body: String,

    @SerializedName(USER_ID)
    val userId: Int

) {

    companion object{
        const val ID = "id"
        const val TITLE = "title"
        const val BODY = "body"
        const val USER_ID = "userId"
    }

}