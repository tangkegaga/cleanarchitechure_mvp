package au.com.tangke.tram.ui.model

open class ApiResponseViewData<T>(
        open var errorMessage: String? = null,

        open var hasError: Boolean? = null,

        open var hasResponse: Boolean? = null,

        open var responseObject: List<T>? = null) {
}