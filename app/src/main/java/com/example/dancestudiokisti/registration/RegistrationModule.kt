package com.example.dancestudiokisti.registration

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
            .baseUrl("https://api.backendless.com/FFE00D00-0065-AC54-FF29-8E6A4D2D4200/1E545F28-B2D8-43E5-9BF3-0E1E350F051D/")
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
        return retrofit.create(RegistrationApi::class.java)
    }
}