package au.com.tangke.tram.domain.interactor.repository

import au.com.tangke.tram.domain.interactor.model.TokenEntity
import au.com.tangke.tram.domain.interactor.model.TramEntity
import io.reactivex.Flowable


interface ITramApiRepository {

    fun getTramEntity(stopId: String, tkn: String): Flowable<TramEntity>

    fun getTokenEntity(): Flowable<TokenEntity>
}