package au.com.tangke.tram.ui.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.tangke.tram.R
import au.com.tangke.tram.appComponent
import au.com.tangke.tram.domain.interactor.usecase.GetTokenEntityUseCase
import au.com.tangke.tram.domain.interactor.usecase.GetTramEntityUseCase
import au.com.tangke.tram.ui.TramRecyleViewAdapter
import au.com.tangke.tram.ui.mapper.TokenViewDataMapper
import au.com.tangke.tram.ui.mapper.TramViewDataMapper
import au.com.tangke.tram.ui.model.TramViewData
import au.com.tangke.tram.ui.mvvm.NORTH_STOP_ID
import au.com.tangke.tram.ui.mvvm.SOUTH_STOP_ID
import kotlinx.android.synthetic.main.tram_list_fragment.*
import javax.inject.Inject

class TramListFragment : Fragment(), TramListContract.View {

    @Inject
    lateinit var getTramUseCase: GetTramEntityUseCase
    @Inject
    lateinit var getTokenUseCase: GetTokenEntityUseCase
    @Inject
    lateinit var tramViewDataMapper: TramViewDataMapper
    @Inject
    lateinit var tokenViewDataMapper: TokenViewDataMapper

    lateinit var presenter: TramListPresenter

    companion object {
        fun newInstance() = TramListFragment()
    }

    private lateinit var northRecyleViewAdapter: TramRecyleViewAdapter
    private lateinit var southRecyleViewAdapter: TramRecyleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
        presenter = TramListPresenter(this, getTramUseCase, getTokenUseCase, tramViewDataMapper, tokenViewDataMapper)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.tram_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    /*
    * init views
    * */

    fun initView() {
        refreshButton.setOnClickListener {
            //delegate to presenter to handle
            presenter.getToken()
        }

        clearButton.setOnClickListener {
            //delegate to presenter to handle
            presenter.clearTramData()
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


    override fun showLoadingView(show: Boolean) {
        progressbar.visibility = if (show == true) View.VISIBLE else View.GONE
    }

    override fun showTrams(viewData: TramViewData?) {
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

    override fun showErrorView(msg: String?) {

    }


}
