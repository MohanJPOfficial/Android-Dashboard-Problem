package zuper.dev.android.dashboard.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import zuper.dev.android.dashboard.data.remote.ApiDataSource
import zuper.dev.android.dashboard.data.repository.DataRepositoryImpl
import zuper.dev.android.dashboard.domain.repository.DataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiDataSource() = ApiDataSource()

    @Provides
    @Singleton
    fun provideDataRepository(apiDataSource: ApiDataSource): DataRepository {
        return DataRepositoryImpl(apiDataSource)
    }
}