package au.com.tangke.tram.data.dataStoreFactory.source


import au.com.tangke.tram.data.dataStoreFactory.repository.RemoteDataStore
import au.com.tangke.tram.data.dataStoreFactory.repository.TramRemote
import au.com.tangke.tram.data.dataStoreFactory.response.TokenData
import au.com.tangke.tram.data.dataStoreFactory.response.TramData
import io.reactivex.Flowable
import javax.inject.Inject


class TramRemoteDataStore @Inject constructor(private val remote: TramRemote)
    : RemoteDataStore {
    override fun getTramData(stopId: String, tkn: String): Flowable<TramData> {
        return remote.getTramData(stopId, tkn)
    }

    override fun getTokenData(): Flowable<TokenData> {
        return remote.getTokenData()
    }


}