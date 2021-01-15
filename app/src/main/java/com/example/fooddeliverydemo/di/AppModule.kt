package com.example.fooddeliverydemo.di

import android.content.Context
import android.os.StrictMode
import com.example.fooddeliverydemo.network.FoodService
import com.example.fooddeliverydemo.room.AppDatabase
import com.example.fooddeliverydemo.room.CartDao
import com.example.fooddeliverydemo.room.FoodDao
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideService(mockWebServer: MockWebServer): FoodService {
        val prevMode = StrictMode.getThreadPolicy()
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        val service = Retrofit.Builder()
                .baseUrl(mockWebServer.url(""))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(FoodService::class.java)
        StrictMode.setThreadPolicy(prevMode)
        return service
    }

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideFoodDao(db: AppDatabase): FoodDao = db.foodDao()

    @Singleton
    @Provides
    fun provideCartDao(db: AppDatabase): CartDao = db.cartDao()

    @Singleton
    @Provides
    fun provideMockWebServer(): MockWebServer {
        return MockWebServer()
//            .apply {
//                val thread: Thread = object : Thread() {
//                    override fun run() {
//                        try {
//                            this@apply.start()
//                        } catch (e: IOException) {
//                            e.printStackTrace()
//                        }
//                    }
//                }
//                thread.start()
//            }
    }


}

@AssistedModule
@DisableInstallInCheck
@InstallIn(ApplicationComponent::class)
@Module
//    (includes = [AssistedInject_AssistedModule::class])
interface AssistedModule

//@AssistedModule
//@InstallIn(ApplicationComponent::class)
//@Module(includes = [AssistedInject_AssistedModule::class])
//abstract class AssistedModule