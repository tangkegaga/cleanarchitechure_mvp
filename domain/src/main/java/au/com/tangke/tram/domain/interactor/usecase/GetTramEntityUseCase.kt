package au.com.tangke.tram.domain.interactor.usecase

import au.com.tangke.tram.domain.interactor.base.FlowableUseCase
import au.com.tangke.tram.domain.executor.PostExecutionThread
import au.com.tangke.tram.domain.executor.ThreadExecutor
import au.com.tangke.tram.domain.interactor.model.TramEntity
import au.com.tangke.tram.domain.interactor.repository.ITramApiRepository
import io.reactivex.Flowable
import javax.inject.Inject


open class GetTramEntityUseCase @Inject constructor(private val iTramApiRepository: ITramApiRepository,
                                                    threadExecutor: ThreadExecutor,
                                                    postExecutionThread: PostExecutionThread)
    : FlowableUseCase<TramEntity, Array<Any>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Array<Any>?): Flowable<TramEntity> {
        val stopId: String = params!![0] as String
        val token: String = params[1] as String

        return iTramApiRepository.getTramEntity(stopId, token)
    }
}