package au.com.tangke.tram.data.dataStoreFactory


import au.com.tangke.tram.data.dataStoreFactory.source.TramStoreFactory
import au.com.tangke.tram.data.dataStoreFactory.mapper.TokenMapper
import au.com.tangke.tram.data.dataStoreFactory.mapper.TramMapper
import au.com.tangke.tram.domain.interactor.model.TokenEntity
import au.com.tangke.tram.domain.interactor.model.TramEntity
import au.com.tangke.tram.domain.interactor.repository.ITramApiRepository
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TramApiRepository @Inject constructor(private val tramStoreFactory: TramStoreFactory,
                                            private val tramMapper: TramMapper,
                                            private val tokenMapper: TokenMapper)
    : ITramApiRepository {

    override fun getTramEntity(stopId: String, tkn: String): Flowable<TramEntity> {

        return tramStoreFactory.retrieveRemoteDataStore().getTramData(stopId, tkn).flatMap {
            Flowable.just(tramMapper.mapToEntity(it))

        }
    }

    override fun getTokenEntity(): Flowable<TokenEntity> {

        return tramStoreFactory.retrieveRemoteDataStore().getTokenData().flatMap {
            Flowable.just(tokenMapper.mapToEntity(it))

        }
    }


}