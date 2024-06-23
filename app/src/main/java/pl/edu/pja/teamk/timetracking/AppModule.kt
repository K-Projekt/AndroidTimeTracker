package pl.edu.pja.teamk.timetracking

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.edu.pja.teamk.timetracking.ui.TimeEntryDetailsViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTimeEntryStore(): TimeEntryStore {
        return TimeEntryStore()
    }

    @Provides
    @Singleton
    fun provideTimeEntryDetailsViewModel(): TimeEntryDetailsViewModel {
        return TimeEntryDetailsViewModel()
    }
}