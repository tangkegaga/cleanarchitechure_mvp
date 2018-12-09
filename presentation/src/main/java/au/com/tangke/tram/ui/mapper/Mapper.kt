package au.com.tangke.tram.ui.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <S> src
 * @param <D> destination
 */
interface Mapper<out Destination, in Source> {

    fun mapToView(type: Source): Destination

}