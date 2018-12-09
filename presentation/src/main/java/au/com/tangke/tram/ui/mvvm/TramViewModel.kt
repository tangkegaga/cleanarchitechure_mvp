package au.com.tangke.tram.ui.mvvm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import au.com.tangke.tram.domain.interactor.model.TokenEntity
import au.com.tangke.tram.domain.interactor.model.TramEntity
import au.com.tangke.tram.domain.interactor.usecase.GetTokenEntityUseCase
import au.com.tangke.tram.domain.interactor.usecase.GetTramEntityUseCase
import au.com.tangke.tram.ui.mapper.TokenViewDataMapper
import au.com.tangke.tram.ui.mapper.TramViewDataMapper
import au.com.tangke.tram.ui.model.TokenViewData
import au.com.tangke.tram.ui.model.TramViewData
import au.com.tangke.tram.util.Resource
import au.com.tangke.tram.util.ResourceState
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

const val NORTH_STOP_ID = "4055"
const val SOUTH_STOP_ID = "4155"

class TramViewModel @Inject constructor(private val getTramUseCase: GetTramEntityUseCase,
                                        private val getTokenUseCase: GetTokenEntityUseCase,
                                        private val tramViewDataMapper: TramViewDataMapper,
                                        private val tokenViewDataMapper: TokenViewDataMapper)
    : ViewModel() {


    var northTramEntityLiveData: MutableLiveData<Resource<TramViewData>> =
            MutableLiveData()

    var southTramEntityLiveData: MutableLiveData<Resource<TramViewData>> =
            MutableLiveData()

    var tokenEntityLiveData: MutableLiveData<Resource<TokenViewData>> =
            MutableLiveData()

    //since there are 3 api calls happens once for one user action, use a seperate isLoadingDataLiveData
    //to indicate the api loading progress, see the xml with databinding
    var isLoadingDataLiveData: MutableLiveData<Boolean> = MutableLiveData()


    var token: String? = null

    override fun onCleared() {
        getTramUseCase.dispose()
        getTokenUseCase.dispose()
        super.onCleared()
    }


    fun getToken() {
        isLoadingDataLiveData.postValue(true)
        getTokenUseCase.execute(GetTokenEntitySubscriber(), null)

    }


    fun clearTramData() {
        tokenEntityLiveData.postValue(
                Resource(ResourceState.SUCCESS, null, null))
        northTramEntityLiveData.postValue(
                Resource(ResourceState.SUCCESS, null, null))
        southTramEntityLiveData.postValue(
                Resource(ResourceState.SUCCESS, null, null))

    }

    fun getTram(stopId: String?, token: String?) {

        if (!token.isNullOrEmpty() && !stopId.isNullOrEmpty()) {
            val params = arrayListOf<String>()
            params.add(stopId!!)
            params.add(token!!)
            getTramUseCase.execute(GetTramEntitySubscriber(stopId), params.toTypedArray())

        }


    }


    inner class GetTokenEntitySubscriber() : DisposableSubscriber<TokenEntity>() {

        override fun onComplete() {}

        override fun onNext(t: TokenEntity?) {
            t?.let {
                token = t.responseObject?.get(0)?.deviceToken
                tokenEntityLiveData.postValue(Resource(ResourceState.SUCCESS, tokenViewDataMapper.mapToView(it), null))

            }
        }

        override fun onError(exception: Throwable) {
            exception.printStackTrace()
            tokenEntityLiveData.postValue(Resource(ResourceState.ERROR, null, null))
            isLoadingDataLiveData.postValue(false)

        }

    }


    inner class GetTramEntitySubscriber(val stopId: String) : DisposableSubscriber<TramEntity>() {

        override fun onComplete() {}

        override fun onNext(t: TramEntity?) {
            if (t != null) {
                when (stopId) {
                    /*this.stopId = NORTH_STOP_ID is to mark which stopId this tram data belong to*/
                    NORTH_STOP_ID ->
                        northTramEntityLiveData.postValue(
                                Resource(ResourceState.SUCCESS, tramViewDataMapper.mapToView(t).apply { this.stopId = NORTH_STOP_ID }, null))
                    SOUTH_STOP_ID -> {
                        southTramEntityLiveData.postValue(
                                Resource(ResourceState.SUCCESS, tramViewDataMapper.mapToView(t).apply { this.stopId = SOUTH_STOP_ID }, null))

                        isLoadingDataLiveData.postValue(false)
                    }
                    else -> {
                    }
                }


            }
        }

        override fun onError(exception: Throwable) {
            exception.printStackTrace()
            when (stopId) {
                NORTH_STOP_ID ->
                    northTramEntityLiveData.postValue(Resource(ResourceState.ERROR, null, null))
                SOUTH_STOP_ID ->
                    southTramEntityLiveData.postValue(Resource(ResourceState.ERROR, null, null))
                else -> {
                }
            }
            isLoadingDataLiveData.postValue(false)

        }

    }
}
