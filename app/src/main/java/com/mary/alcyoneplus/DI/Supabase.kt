package com.mary.alcyoneplus.DI


import com.mary.alcyoneplus.BuildConfig
import com.mary.alcyoneplus.Data.NetworkRequests
import com.mary.alcyoneplus.Data.RepositoryImpl
import com.mary.alcyoneplus.Data.repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}

