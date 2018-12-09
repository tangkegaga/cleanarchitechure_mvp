package au.com.tangke.tram.ui.mvp

import au.com.tangke.tram.ui.model.TramViewData

interface TramListContract {

    interface View {
        fun showLoadingView(show: Boolean)
        fun showTrams(viewData: TramViewData?)
        fun showErrorView(msg: String?)

    }

    interface Presenter {
        fun getToken()
        fun getTram(stopId: String?, token: String?)
        fun clearTramData()
    }


}