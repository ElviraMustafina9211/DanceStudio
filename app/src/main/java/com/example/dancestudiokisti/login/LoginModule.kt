package com.example.dancestudiokisti.login

import com.example.dancestudiokisti.TokenSaver
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class LoginModule {

    @Provides
    fun loginRepository(loginApi: LoginApi, tokenSaver: TokenSaver): LoginRepository {
        return LoginRepository(loginApi, tokenSaver)
    }

    @Provides
    fun loginViewModelFactory(loginRepository: LoginRepository): LoginViewModelFactory {
        return LoginViewModelFactory(loginRepository)
    }

    @Provides
    fun loginApi(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): LoginApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.backendless.com/FFE00D00-0065-AC54-FF29-8E6A4D2D4200/1E545F28-B2D8-43E5-9BF3-0E1E350F051D/")
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
        return retrofit.create(LoginApi::class.java)
    }
}