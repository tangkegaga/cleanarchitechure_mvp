package au.com.tangke.tram.data.net


import au.com.tangke.tram.data.dataStoreFactory.response.TokenData
import au.com.tangke.tram.data.dataStoreFactory.response.TramData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface TramApiService {


    @GET("/TramTracker/RestService/GetDeviceToken/?aid=TTIOSJSON&devInfo=HomeTimeAndroid")
    fun getToken(): Flowable<TokenData>

    @GET("/TramTracker/RestService//GetNextPredictedRoutesCollection/{stopId}/78/false/?aid=TTIOSJSON&cid=2")
    fun getTrams(
            @Path("stopId") stopId: String,
            @Query("tkn") token: String
    ): Flowable<TramData>


}