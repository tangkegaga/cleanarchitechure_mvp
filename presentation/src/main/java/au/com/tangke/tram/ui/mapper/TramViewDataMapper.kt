package au.com.tangke.tram.ui.mapper

import au.com.tangke.tram.domain.interactor.model.TramEntity
import au.com.tangke.tram.ui.model.TramViewData
import javax.inject.Inject

open class TramViewDataMapper @Inject constructor(private val tramItemViewDataMapper: TramItemViewDataMapper)
    : Mapper<TramViewData, TramEntity> {
    override fun mapToView(type: TramEntity): TramViewData {
        val viewData = TramViewData()
        with(viewData) {
            this.errorMessage = type.errorMessage
            this.hasError = type.hasError
            this.hasResponse = type.hasResponse
            this.responseObject = type.responseObject?.map { tramItemViewDataMapper.mapToView(it) }

        }
        return viewData
    }
}