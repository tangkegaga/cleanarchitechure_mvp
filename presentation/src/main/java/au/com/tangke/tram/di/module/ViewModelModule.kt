package au.com.tangke.tram.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import au.com.tangke.tram.ui.mvvm.TramViewModel
import au.com.tangke.tram.util.ViewModelFactory
import au.com.tangke.tram.util.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TramViewModel::class)
    internal abstract fun tramViewModel(viewModel: TramViewModel): ViewModel

}