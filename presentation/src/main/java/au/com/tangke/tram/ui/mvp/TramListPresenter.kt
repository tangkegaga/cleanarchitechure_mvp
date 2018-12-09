package au.com.tangke.tram.ui.mvp

import au.com.tangke.tram.domain.interactor.model.TokenEntity
import au.com.tangke.tram.domain.interactor.model.TramEntity
import au.com.tangke.tram.domain.interactor.usecase.GetTokenEntityUseCase
import au.com.tangke.tram.domain.interactor.usecase.GetTramEntityUseCase
import au.com.tangke.tram.ui.mapper.TokenViewDataMapper
import au.com.tangke.tram.ui.mapper.TramViewDataMapper
import au.com.tangke.tram.ui.mvvm.NORTH_STOP_ID
import au.com.tangke.tram.ui.mvvm.SOUTH_STOP_ID
import io.reactivex.subscribers.DisposableSubscriber

const val NORTH_STOP_ID = "4055"
const val SOUTH_STOP_ID = "4155"

class TramListPresenter constructor(
        private val view: TramListContract.View,
        private val getTramUseCase: GetTramEntityUseCase,
        private val getTokenUseCase: GetTokenEntityUseCase,
        private val tramViewDataMapper: TramViewDataMapper,
        private val tokenViewDataMapper: TokenViewDataMapper) : TramListContract.Presenter {


    override fun getToken() {
        view.showLoadingView(true)
        getTokenUseCase.execute(GetTokenEntitySubscriber(), null)


    }

    override fun getTram(stopId: String?, token: String?) {

        if (!token.isNullOrEmpty() && !stopId.isNullOrEmpty()) {
            val params = arrayListOf<String>()
            params.add(stopId!!)
            params.add(token!!)
            getTramUseCase.execute(GetTramEntitySubscriber(stopId), params.toTypedArray())

        }


    }

    override fun clearTramData() {
        view.showTrams(null)
    }


    inner class GetTokenEntitySubscriber() : DisposableSubscriber<TokenEntity>() {

        override fun onComplete() {}

        override fun onNext(t: TokenEntity?) {
            t?.let {

                val tokenViewData = tokenViewDataMapper.mapToView(it)
                val token = tokenViewData.responseObject?.get(0)?.deviceToken
                if (!token.isNullOrEmpty()) {
                    getTram(NORTH_STOP_ID, token)
                    getTram(SOUTH_STOP_ID, token)
                }


            }
        }

        override fun onError(exception: Throwable) {
            exception.printStackTrace()
            view.showLoadingView(false)
            view.showErrorView(exception.message)

        }

    }


    inner class GetTramEntitySubscriber(val stopId: String) : DisposableSubscriber<TramEntity>() {

        override fun onComplete() {}

        override fun onNext(t: TramEntity?) {
            if (t != null) {
                when (stopId) {
                    /*this.stopId = NORTH_STOP_ID is to mark which stopId this tram data belong to*/
                    NORTH_STOP_ID ->
                        view.showTrams(tramViewDataMapper.mapToView(t).apply { this.stopId = NORTH_STOP_ID })

                    SOUTH_STOP_ID -> {
                        view.showTrams(tramViewDataMapper.mapToView(t).apply { this.stopId = SOUTH_STOP_ID })

                        view.showLoadingView(false)
                    }
                    else -> {
                    }
                }


            }
        }

        override fun onError(exception: Throwable) {
            exception.printStackTrace()
            view.showLoadingView(false)
            view.showErrorView(exception.message)

        }

    }
}