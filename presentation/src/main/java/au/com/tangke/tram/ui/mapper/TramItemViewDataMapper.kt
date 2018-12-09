package au.com.tangke.tram.ui.mapper

import au.com.tangke.tram.domain.interactor.model.TramItemEntity
import au.com.tangke.tram.ui.model.TramItemViewData
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import android.text.format.DateUtils
import android.text.format.DateUtils.getRelativeTimeSpanString


open class TramItemViewDataMapper @Inject constructor() : Mapper<TramItemViewData, TramItemEntity> {
    override fun mapToView(type: TramItemEntity): TramItemViewData {
        return TramItemViewData(type.destination,
                type.predictedArrival?.let { dateFromDotNetDate(it) },
                type.routeNo,
                type.predictedArrival?.let { convertToLeftTime(it) })

    }

    /////////////
    // Convert .NET Date to Date
    ////////////
    private fun dateFromDotNetDate(dotNetDate: String): String {

        val startIndex = dotNetDate.indexOf("(") + 1
        val endIndex = dotNetDate.indexOf("+")
        val date = dotNetDate.substring(startIndex, endIndex)

        val unixTime = java.lang.Long.parseLong(date)
        val dateUnix = Date(unixTime)
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");
        return sdf.format(dateUnix)

    }

    /*calculate time left*/

    private fun convertToLeftTime(dotNetDate: String): String {

        val startIndex = dotNetDate.indexOf("(") + 1
        val endIndex = dotNetDate.indexOf("+")
        val date = dotNetDate.substring(startIndex, endIndex)

        val unixTime = java.lang.Long.parseLong(date)
        val dateUnix = Date(unixTime)
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // it comes out like this 2013-08-31 15:55:22 so adjust the date format
        /*val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = sdf.parse(dateUnix.toString())*/
        val epoch = dateUnix.time

        return getRelativeTimeSpanString(epoch, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString()


    }
}