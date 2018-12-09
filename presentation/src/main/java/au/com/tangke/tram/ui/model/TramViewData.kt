package au.com.tangke.tram.ui.model

open class TramViewData : ApiResponseViewData<TramItemViewData>() {
    open var stopId: String? = null
}