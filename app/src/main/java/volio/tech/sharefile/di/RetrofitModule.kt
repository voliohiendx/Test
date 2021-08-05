package volio.tech.sharefile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import volio.tech.sharefile.framework.datasource.network.api.DummyApi
import volio.tech.sharefile.framework.datasource.network.api.NetworkConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideDummyApi(retrofit: Retrofit): DummyApi =
        retrofit.create(DummyApi::class.java)


}