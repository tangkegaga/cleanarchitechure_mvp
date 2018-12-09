package au.com.tangke.tram.domain.interactor.usecase

import au.com.tangke.tram.domain.interactor.base.FlowableUseCase
import au.com.tangke.tram.domain.executor.PostExecutionThread
import au.com.tangke.tram.domain.executor.ThreadExecutor
import au.com.tangke.tram.domain.interactor.model.TokenEntity
import au.com.tangke.tram.domain.interactor.repository.ITramApiRepository
import io.reactivex.Flowable
import javax.inject.Inject


open class GetTokenEntityUseCase @Inject constructor(private val iTramApiRepository: ITramApiRepository,
                                                     threadExecutor: ThreadExecutor,
                                                     postExecutionThread: PostExecutionThread)
    : FlowableUseCase<TokenEntity, Array<Any>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Array<Any>?): Flowable<TokenEntity> {

        return iTramApiRepository.getTokenEntity()
    }
}