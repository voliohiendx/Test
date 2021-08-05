package volio.tech.sharefile.framework.datasource.network.api

import volio.tech.sharefile.framework.datasource.network.api.NetworkConstants.POSTS
import volio.tech.sharefile.framework.datasource.network.model.response.DummyDto
import retrofit2.http.GET

interface DummyApi {

    @GET(POSTS)
    suspend fun getDummies(): List<DummyDto>

    //example of post body
//    @POST(POSTS)
//    suspend fun postDummies(
//        @Body fakeRequest: DummyFakeRequest
//    )

}