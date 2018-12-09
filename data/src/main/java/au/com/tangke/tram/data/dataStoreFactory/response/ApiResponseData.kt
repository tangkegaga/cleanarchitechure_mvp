package au.com.tangke.tram.data.dataStoreFactory.response

import com.google.gson.annotations.SerializedName

open class ApiResponseData<T>(@field:SerializedName("errorMessage")
                              var errorMessage: String? = null,
                              @field:SerializedName("hasError")
                              var hasError: Boolean? = null,
                              @field:SerializedName("hasResponse")
                              var hasResponse: Boolean? = null,
                              @field:SerializedName("responseObject")
                              var responseObject: List<T>? = null) {

}