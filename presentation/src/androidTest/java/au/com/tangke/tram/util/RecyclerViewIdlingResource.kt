package au.com.tangke.tram.util

import android.support.test.espresso.IdlingResource
import android.support.v7.widget.RecyclerView

class RecyclerViewIdlingResource(private val recyclerView: RecyclerView) : IdlingResource {

    private var mIdle: Boolean = false
    private var mResourceCallback: IdlingResource.ResourceCallback? = null

    init {
        this.mIdle = false
        this.mResourceCallback = null
    }

    override fun getName(): String {
        return RecyclerViewIdlingResource::class.java.simpleName
    }

    override fun isIdleNow(): Boolean {
        mIdle = recyclerView.childCount > 0
        if (mIdle) {
            if (mResourceCallback != null) {
                mResourceCallback!!.onTransitionToIdle()
            }
        }

        return mIdle
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        mResourceCallback = resourceCallback
    }

}

