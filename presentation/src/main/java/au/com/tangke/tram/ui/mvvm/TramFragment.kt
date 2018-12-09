package au.com.tangke.tram.ui.mvvm

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.tangke.tram.R
import au.com.tangke.tram.appComponent
import au.com.tangke.tram.databinding.TramFragmentBinding
import au.com.tangke.tram.ui.TramRecyleViewAdapter
import au.com.tangke.tram.ui.model.TramViewData
import au.com.tangke.tram.util.ResourceState
import au.com.tangke.tram.util.ViewModelFactory
import au.com.tangke.tram.util.getViewModel
import au.com.tangke.tram.util.initObserver
import kotlinx.android.synthetic.main.tram_fragment.*
import javax.inject.Inject

class TramFragment : Fragment() {
    /*
    * please refer to see how it works if needed
    * https://proandroiddev.com/viewmodel-with-dagger2-architecture-components-2e06f06c9455
    * */
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var binding: TramFragmentBinding


    companion object {
        fun newInstance() = TramFragment()
    }

    private lateinit var viewModel: TramViewModel
    private lateinit var northRecyleViewAdapter: TramRecyleViewAdapter
    private lateinit var southRecyleViewAdapter: TramRecyleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
        /*
        * please refer to see how it works if needed
        * https://proandroiddev.com/viewmodel-with-dagger2-architecture-components-2e06f06c9455
        * */
        viewModel = this.getViewModel(viewModelFactory)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.tram_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind<ViewDataBinding>(view) as TramFragmentBinding

        initViewModel()
        initView()
        binding.viewModel = viewModel


    }

    /*
    * register the observers to observe the data we are interested, and handle the data
    * */
    private fun initViewModel() {

        //after token is received, try to getTram
        viewModel.tokenEntityLiveData.initObserver(this) {
            if (it != null) {
                when (it.status) {
                    ResourceState.SUCCESS -> {
                        val token = it.data?.responseObject?.get(0)?.deviceToken
                        if (!token.isNullOrEmpty()) {
                            viewModel.getTram(NORTH_STOP_ID, token)
                            viewModel.getTram(SOUTH_STOP_ID, token)
                        }

                    }
                    else -> {
                    }
                }


            }

        }

        //after north trams is received, render it
        viewModel.northTramEntityLiveData.initObserver(this) {
            if (it != null) {
                when (it.status) {
                    ResourceState.SUCCESS -> {
                        showTrams(it.data)
                    }
                    else -> {
                    }
                }


            }

        }

        //after south trams is received, render it
        viewModel.southTramEntityLiveData.initObserver(this) {
            if (it != null) {
                when (it.status) {
                    ResourceState.SUCCESS -> {
                        showTrams(it.data)
                    }
                    else -> {
                    }
                }
            }
        }


    }

    /*
    * init views
    * */

    fun initView() {
        binding.refreshButton.setOnClickListener {
            //delegate to viewmodel to handle
            viewModel.getToken()
        }

        binding.clearButton.setOnClickListener {
            //delegate to viewmodel to handle
            viewModel.clearTramData()
        }

        northRecyleViewAdapter = TramRecyleViewAdapter()
        southRecyleViewAdapter = TramRecyleViewAdapter()

        northRecyclerView.apply {
            hasFixedSize()
            addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
        }
        southRecyclerView.apply {
            hasFixedSize()
            addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
        }
    }

    /*
    * Render trams in recyclerview
    * */
    private fun showTrams(viewData: TramViewData?) {

        when (viewData?.stopId) {
            NORTH_STOP_ID -> {
                northRecyleViewAdapter.tramItemList = viewData.responseObject?.toMutableList()
                northRecyclerView.adapter = northRecyleViewAdapter
                northRecyleViewAdapter.notifyDataSetChanged()
            }
            SOUTH_STOP_ID -> {
                southRecyleViewAdapter.tramItemList = viewData.responseObject?.toMutableList()
                southRecyclerView.adapter = southRecyleViewAdapter
                southRecyleViewAdapter.notifyDataSetChanged()
            }
            else -> {
                northRecyleViewAdapter.tramItemList?.clear()
                southRecyleViewAdapter.tramItemList?.clear()
                northRecyleViewAdapter.notifyDataSetChanged()
                southRecyleViewAdapter.notifyDataSetChanged()
            }
        }
    }

}
