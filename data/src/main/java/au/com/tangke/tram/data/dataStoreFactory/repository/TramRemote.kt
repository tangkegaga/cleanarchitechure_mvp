package au.com.tangke.tram.data.dataStoreFactory.repository


import au.com.tangke.tram.data.dataStoreFactory.response.TokenData
import au.com.tangke.tram.data.dataStoreFactory.response.TramData
import io.reactivex.Flowable


interface TramRemote {

    fun getTramData(stopId: String, tkn: String): Flowable<TramData>

    fun getTokenData(): Flowable<TokenData>
}