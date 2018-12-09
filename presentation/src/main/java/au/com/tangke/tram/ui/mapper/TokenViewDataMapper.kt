package au.com.tangke.tram.ui.mapper

import au.com.tangke.tram.domain.interactor.model.TokenEntity
import au.com.tangke.tram.ui.model.TokenViewData
import javax.inject.Inject

open class TokenViewDataMapper @Inject constructor(private val tokenItemViewDataMapper: TokenItemViewDataMapper)
    : Mapper<TokenViewData, TokenEntity> {
    override fun mapToView(type: TokenEntity): TokenViewData {
        val viewData = TokenViewData()
        with(viewData) {
            this.errorMessage = type.errorMessage
            this.hasError = type.hasError
            this.hasResponse = type.hasResponse
            this.responseObject = type.responseObject?.map { tokenItemViewDataMapper.mapToView(it) }

        }
        return viewData
    }
}