package io.github.kabirnayeem99.friends.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.kabirnayeem99.friends.data.repo.RandomUserRepository

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideRandomUserRepository(): RandomUserRepository {
        return RandomUserRepository()
    }
}