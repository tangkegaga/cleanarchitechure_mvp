package au.com.tangke.tram.data.dataStoreFactory.mapper




import au.com.tangke.tram.data.dataStoreFactory.response.TokenItemData
import au.com.tangke.tram.domain.interactor.model.TokenItemEntity
import javax.inject.Inject

open class TokenItemMapper @Inject constructor()
    : Mapper<TokenItemEntity, TokenItemData> {


    override fun mapToEntity(type: TokenItemData): TokenItemEntity {
        return TokenItemEntity(type.deviceToken)
    }
}