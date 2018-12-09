package au.com.tangke.tram.domain.interactor.model

import com.google.gson.annotations.SerializedName

open class ApiResponseEntity<T>(
        open var errorMessage: String? = null,

        open var hasError: Boolean? = null,

        open var hasResponse: Boolean? = null,

        open var responseObject: List<T>? = null) {

}