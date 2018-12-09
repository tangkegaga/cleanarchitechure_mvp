package au.com.tangke.tram.data.dataStoreFactory.response

import com.google.gson.annotations.SerializedName

open class TramItemData(@field:SerializedName("Destination")
                        var destination: String? = null,
                        @field:SerializedName("PredictedArrivalDateTime")
                        var predictedArrival: String? = null,
                        @field:SerializedName("RouteNo")
                        var routeNo: String? = null
)
