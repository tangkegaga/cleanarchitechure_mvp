package au.com.tangke.tram.data.dataStoreFactory.mapper

import au.com.tangke.tram.data.dataStoreFactory.response.TramData
import au.com.tangke.tram.domain.interactor.model.TramEntity
import javax.inject.Inject

open class TramMapper @Inject constructor(private val tramItemMapper: TramItemMapper)
    : Mapper<TramEntity, TramData> {


    override fun mapToEntity(type: TramData): TramEntity {
        val tramEntity = TramEntity()
        with(tramEntity) {
            this.errorMessage = type.errorMessage
            this.hasError = type.hasError
            this.hasResponse = type.hasResponse
            this.responseObject = type.responseObject?.map { tramItemMapper.mapToEntity(it) }

        }
        return tramEntity
    }
}