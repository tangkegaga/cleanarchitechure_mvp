package au.com.tangke.tram.ui.mvvm.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import au.com.tangke.tram.domain.interactor.model.TokenEntity
import au.com.tangke.tram.domain.interactor.usecase.GetTokenEntityUseCase
import au.com.tangke.tram.domain.interactor.usecase.GetTramEntityUseCase
import au.com.tangke.tram.util.mock
import au.com.tangke.tram.ui.mapper.TokenItemViewDataMapper
import au.com.tangke.tram.ui.mapper.TokenViewDataMapper
import au.com.tangke.tram.ui.mapper.TramItemViewDataMapper
import au.com.tangke.tram.ui.mapper.TramViewDataMapper
import au.com.tangke.tram.ui.mvvm.TramViewModel
import au.com.tangke.tram.util.ResourceState
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class TramViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val getTramUseCase = mock<GetTramEntityUseCase>()
    val getTokenUseCase = mock<GetTokenEntityUseCase>()


    private lateinit var tramViewModel: TramViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val tramViewDataMapper = TramViewDataMapper(TramItemViewDataMapper())
        val tokenViewDataMapper = TokenViewDataMapper(TokenItemViewDataMapper())

        tramViewModel = TramViewModel(getTramUseCase, getTokenUseCase, tramViewDataMapper, tokenViewDataMapper)
    }

    @Test
    fun getToken_TokenPosted() {
        val tokenEntity = TokenEntity()

        tramViewModel.getToken()

        argumentCaptor<TramViewModel.GetTokenEntitySubscriber>().apply {
            verify(getTokenUseCase).execute(capture(), eq(null))
            firstValue.onNext(tokenEntity)

            // if status is equal to .ResourceState.SUCCESS, means token is updated
            assert(tramViewModel.tokenEntityLiveData.value?.status == ResourceState.SUCCESS)

        }

    }


}
