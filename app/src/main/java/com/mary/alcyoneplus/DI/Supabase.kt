package com.mary.alcyoneplus.DI


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.mary.alcyoneplus.BuildConfig
import com.mary.alcyoneplus.Data.NetworkRequests
import com.mary.alcyoneplus.Data.RepositoryImpl
import com.mary.alcyoneplus.Data.ScheduleOfflineRepository
import com.mary.alcyoneplus.Data.repository
import com.mary.alcyoneplus.utils.ConnectivityObserver
import com.mary.alcyoneplus.utils.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object Supabase {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = BuildConfig.URL,
            supabaseKey = BuildConfig.API_KEY,
        ) {
            install(Postgrest)
            install(Auth)
        }
    }

    @Provides
    @Singleton
    fun provideSupabaseDatabase(client: SupabaseClient): Postgrest {
        return client.postgrest
    }

    @Provides
    @Singleton
    fun provideRepository(
        source: NetworkRequests
    ): repository {
        return RepositoryImpl(source)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(
        @ApplicationContext context: Context
    ) = DataStoreManager(context)

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Singleton
    @Provides
    fun provideUserRepository(@ApplicationContext context: Context): ScheduleOfflineRepository {
        return ScheduleOfflineRepository(context)
    }

    @Singleton
    @Provides
    fun provideConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver {
        return ConnectivityObserver(context)
    }
}

