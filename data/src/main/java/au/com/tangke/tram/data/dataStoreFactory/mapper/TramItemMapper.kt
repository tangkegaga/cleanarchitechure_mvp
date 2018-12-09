package au.com.tangke.tram.data.dataStoreFactory.mapper


import au.com.tangke.tram.data.dataStoreFactory.response.TramItemData

import au.com.tangke.tram.domain.interactor.model.TramItemEntity


import javax.inject.Inject

open class TramItemMapper @Inject constructor()
    : Mapper<TramItemEntity, TramItemData> {


    override fun mapToEntity(type: TramItemData): TramItemEntity {
        return TramItemEntity(type.destination, type.predictedArrival, type.routeNo)
    }
}