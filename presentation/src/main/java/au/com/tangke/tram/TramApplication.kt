package au.com.tangke.tram

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.v4.app.Fragment
import au.com.tangke.tram.di.component.ApplicationComponent
import au.com.tangke.tram.di.component.DaggerApplicationComponent

class TramApplication : Application() {

    companion object {
        var applicationComponent: ApplicationComponent? = null

    }

    override fun onCreate() {
        super.onCreate()

    }
}


private fun buildDagger(context: Context): ApplicationComponent {
    if (TramApplication.applicationComponent == null) {
        TramApplication.applicationComponent = DaggerApplicationComponent.builder()
                .application(context as Application)
                .build()

    }
    return TramApplication.applicationComponent!!
}

fun Activity.appComponent(): ApplicationComponent {
    return buildDagger(this.applicationContext)
}


fun Context.appComponent(): ApplicationComponent {
    return buildDagger(this.applicationContext)
}


fun Fragment.appComponent(): ApplicationComponent {
    return buildDagger(this.context!!.applicationContext)
}