package au.com.tangke.tram.data.dataStoreFactory.response

import com.google.gson.annotations.SerializedName

open class TokenItemData {

    @field:SerializedName("DeviceToken")
    var deviceToken: String? = null
}