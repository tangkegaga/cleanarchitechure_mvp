package au.com.tangke.tram.di.module


import au.com.tangke.tram.data.dataStoreFactory.TramApiRepository
import au.com.tangke.tram.data.dataStoreFactory.repository.TramRemote
import au.com.tangke.tram.data.net.repository.TramRemoteImpl
import au.com.tangke.tram.domain.interactor.repository.ITramApiRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {


    @Binds
    abstract fun bindHomeRemote(remote: TramRemoteImpl): TramRemote

    @Binds
    abstract fun bindHomeDataRepository(repository: TramApiRepository): ITramApiRepository



}
