package com.example.dancestudiokisti.login

import com.example.dancestudiokisti.BuildConfig
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
        return LoginRepositoryImpl(loginApi, tokenSaver)
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
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
        return retrofit.create(LoginApi::class.java)
    }
}