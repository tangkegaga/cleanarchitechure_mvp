package au.com.tangke.tram.data.dataStoreFactory.mapper

import au.com.tangke.tram.data.dataStoreFactory.response.TokenData
import au.com.tangke.tram.domain.interactor.model.TokenEntity
import javax.inject.Inject

open class TokenMapper @Inject constructor(private val TokenItemMapper: TokenItemMapper)
    : Mapper<TokenEntity, TokenData> {


    override fun mapToEntity(type: TokenData): TokenEntity {
        val TokenEntity = TokenEntity()
        with(TokenEntity) {
            this.errorMessage = type.errorMessage
            this.hasError = type.hasError
            this.hasResponse = type.hasResponse
            this.responseObject = type.responseObject?.map { TokenItemMapper.mapToEntity(it) }

        }
        return TokenEntity
    }
}