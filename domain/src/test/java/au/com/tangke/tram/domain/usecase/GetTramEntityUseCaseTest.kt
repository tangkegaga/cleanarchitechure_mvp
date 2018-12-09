package au.com.tangke.tram.domain.usecase


import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

import au.com.tangke.tram.domain.executor.PostExecutionThread
import au.com.tangke.tram.domain.executor.ThreadExecutor
import au.com.tangke.tram.domain.interactor.repository.ITramApiRepository
import au.com.tangke.tram.domain.interactor.usecase.GetTramEntityUseCase

import org.mockito.Mockito.verify

@RunWith(MockitoJUnitRunner::class)
class GetTramEntityUseCaseTest {

    private var getTramEntityUseCase: GetTramEntityUseCase? = null

    @Mock
    private val mockThreadExecutor: ThreadExecutor? = null
    @Mock
    private val mockPostExecutionThread: PostExecutionThread? = null
    @Mock
    private val mockTramRepository: ITramApiRepository? = null

    @Before
    fun setUp() {
        getTramEntityUseCase = GetTramEntityUseCase(mockTramRepository!!, mockThreadExecutor!!,
                mockPostExecutionThread!!)
    }

    @Test
    fun testGetUserListUseCaseObservableHappyCase() {
        val params = arrayListOf<String>()
        params.add("4055")
        params.add("fake_token")

        getTramEntityUseCase!!.buildUseCaseObservable(params.toTypedArray())
        verify<ITramApiRepository>(mockTramRepository).getTramEntity("4055", "fake_token")

    }
}
