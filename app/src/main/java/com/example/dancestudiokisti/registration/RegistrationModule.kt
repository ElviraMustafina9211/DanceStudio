package com.example.dancestudiokisti.registration

import com.example.dancestudiokisti.BuildConfig
import com.example.dancestudiokisti.TokenSaver
import com.example.dancestudiokisti.login.LoginApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RegistrationModule {

    @Provides
    fun registrationRepository(
        registrationApi: RegistrationApi,
        loginApi: LoginApi,
        tokenSaver: TokenSaver
    ): RegistrationRepository {
        return RegistrationRepositoryImpl(registrationApi, loginApi, tokenSaver)
    }

    @Provides
    fun registrationViewModelFactory(registrationRepository: RegistrationRepository): RegistrationViewModelFactory {
        return RegistrationViewModelFactory(registrationRepository)
    }

    @Provides
    fun registrationApi(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): RegistrationApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
        return retrofit.create(RegistrationApi::class.java)
    }
}