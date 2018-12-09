package au.com.tangke.tram.util

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

fun <T> LiveData<T>.initObserver(lifecycleOwner: LifecycleOwner, observer: (T?) -> Unit) {
    try {
        removeObservers(lifecycleOwner)
        observe(lifecycleOwner, Observer<T> { t -> observer.invoke(t!!) })
    }catch (e: KotlinNullPointerException){
        e.printStackTrace()

    }

}