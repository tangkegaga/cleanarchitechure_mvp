package au.com.tangke.tram.data.dataStoreFactory.source

import javax.inject.Inject


open class TramStoreFactory @Inject constructor(private val tramRemoteDataStoreToken: TramRemoteDataStore) {

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): TramRemoteDataStore {
        return tramRemoteDataStoreToken
    }
}