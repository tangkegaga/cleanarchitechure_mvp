package au.com.tangke.tram.di.module

import au.com.tangke.tram.domain.executor.PostExecutionThread
import au.com.tangke.tram.domain.executor.ThreadExecutor
import au.com.tangke.tram.executor.JobExecutor
import au.com.tangke.tram.executor.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule() {

    @Provides
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread = uiThread
}