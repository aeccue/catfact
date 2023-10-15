package jp.speakbuddy.catfact.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jp.speakbuddy.catfact.domain.usecase.GetFactWithMultipleCats
import jp.speakbuddy.catfact.domain.usecase.GetFactWithMutlipleCatsUseCase

@Module
@InstallIn(ViewModelComponent::class)
internal interface DomainHiltModule {

    @Binds
    fun bindGetFactWithMultipleCats(useCase: GetFactWithMutlipleCatsUseCase): GetFactWithMultipleCats
}
