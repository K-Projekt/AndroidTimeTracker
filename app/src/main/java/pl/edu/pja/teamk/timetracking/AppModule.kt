package pl.edu.pja.teamk.timetracking

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideTimeEntryStore(): TimeEntryStore {
        return TimeEntryStore()
    }
}