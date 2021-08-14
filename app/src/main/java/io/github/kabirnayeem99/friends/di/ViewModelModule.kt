package io.github.kabirnayeem99.friends.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.kabirnayeem99.friends.data.repository.RandomUserRepositoryImpl
import io.github.kabirnayeem99.friends.data.sources.RemoteDataSource

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun providesRepository(remoteDataSource: RemoteDataSource): RandomUserRepositoryImpl =
        RandomUserRepositoryImpl(remoteDataSource)

}