package com.itsydev.clubvr.di

import com.itsydev.clubvr.domain.users.GetExperiencesByNameUseCase
import com.itsydev.clubvr.domain.users.GetExperiencesByNameUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ExperiencesModule {

    @Singleton
    @Provides
    fun providesGetexperiencesByName()
            = GetExperiencesByNameUseCaseImpl() as GetExperiencesByNameUseCase

}