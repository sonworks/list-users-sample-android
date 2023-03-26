package com.example.repository.di

import com.example.repository.UserRepository
import com.example.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository {
        return impl
    }
}
