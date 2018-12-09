package au.com.tangke.tram.ui

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.tangke.tram.BR
import au.com.tangke.tram.R
import au.com.tangke.tram.ui.model.TramItemViewData


class TramRecyleViewAdapter(var tramItemList: MutableList<TramItemViewData?>? = null) : RecyclerView.Adapter<TramRecyleViewAdapter.BindingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view: View
        view = LayoutInflater.from(parent.context).inflate(R.layout.tram_item, parent, false)
        return BindingHolder(view)

    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val optionViewModel = tramItemList!![position]
        holder.binding!!.setVariable(BR.viewModel, optionViewModel)

    }

    override fun getItemCount(): Int {
        return tramItemList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }


    inner class BindingHolder(rowView: View) : RecyclerView.ViewHolder(rowView) {
        val binding: ViewDataBinding?

        init {
            binding = DataBindingUtil.bind(rowView)
        }

    }


}
