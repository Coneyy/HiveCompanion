package coneys.com.github.hivecompanion.base.repository

import android.arch.lifecycle.LiveData

interface Repository<T> {

    fun fetchData(): LiveData<T>

}
