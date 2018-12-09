package au.com.tangke.tram.di.component

import android.app.Application
import au.com.tangke.tram.di.module.ApplicationModule
import au.com.tangke.tram.di.module.RepositoryModule
import au.com.tangke.tram.di.module.TramAPIModule
import au.com.tangke.tram.di.module.ViewModelModule
import au.com.tangke.tram.domain.executor.PostExecutionThread
import au.com.tangke.tram.domain.executor.ThreadExecutor
import au.com.tangke.tram.ui.TramActivity
import au.com.tangke.tram.ui.mvvm.TramFragment
import au.com.tangke.tram.ui.mvp.TramListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, RepositoryModule::class, ViewModelModule::class,
    TramAPIModule::class])

interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): ApplicationComponent
    }


    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread

    fun inject(baseApplication: Application)

    fun inject(tramActivity: TramActivity)

    fun inject(tramFragment: TramFragment)
    fun inject(tramListFragment: TramListFragment)
}