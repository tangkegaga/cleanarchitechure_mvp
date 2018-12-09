package au.com.tangke.tram.data.net.repository

import au.com.tangke.tram.data.dataStoreFactory.repository.TramRemote
import au.com.tangke.tram.data.dataStoreFactory.response.TokenData
import au.com.tangke.tram.data.dataStoreFactory.response.TramData
import au.com.tangke.tram.data.net.TramApiService
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TramRemoteImpl @Inject constructor(private val tramApiService: TramApiService)
    : TramRemote {
    override fun getTramData(stopId: String, tkn: String): Flowable<TramData> {
        return tramApiService.getTrams(stopId, tkn)
    }

    override fun getTokenData(): Flowable<TokenData> {
        return tramApiService.getToken()
    }


}