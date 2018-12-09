package au.com.tangke.tram.ui.mapper

import au.com.tangke.tram.domain.interactor.model.TokenItemEntity
import au.com.tangke.tram.ui.model.TokenItemViewData
import javax.inject.Inject

open class TokenItemViewDataMapper @Inject constructor() : Mapper<TokenItemViewData, TokenItemEntity> {
    override fun mapToView(type: TokenItemEntity): TokenItemViewData {
        return TokenItemViewData(type.deviceToken)
    }
}