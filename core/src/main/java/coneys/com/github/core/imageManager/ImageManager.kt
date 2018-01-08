package coneys.com.github.core.imageManager

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.lang.ref.WeakReference


class ImageManager : LifecycleObserver {

    var weakContext: WeakReference<Context>? = null

    fun loadImage(url: String, view: ImageView) {


        if (weakContext == null)
            throw RuntimeException("Activity wasn't registered for ImageManager!")

        Glide.with(weakContext?.get()).load(url).into(view)

    }


    fun register(context: Context) {
        val lifecycleOwner = context as LifecycleOwner
        lifecycleOwner.lifecycle.addObserver(this)
        this.weakContext = WeakReference(context)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unregister() {
        this.weakContext = null
    }


}