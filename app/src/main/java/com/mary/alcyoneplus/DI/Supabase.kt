package com.mary.alcyoneplus.DI


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
            supabaseUrl = "https://llltymdulwzpknjqnpgi.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImxsbHR5bWR1bHd6cGtuanFucGdpIiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODgwNzg4MzQsImV4cCI6MjAwMzY1NDgzNH0.K3OMP-StBpmH-LRZqyKozRvqHnBVqGtyiiUXFLIkgyg",
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

