package info.adavis.puppytracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.adavis.puppytracker.ApiClient.pupApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val tag = MainViewModel::class.java.simpleName

    private val _url =  MutableLiveData<String>()
    val url: LiveData<String>
        get() = _url

    private val _log =  MutableLiveData<String>("")
    val log: LiveData<String>
        get() = _log

    init {
        loadRandomPup()
    }

    fun trackPup() {
        loadRandomPup()
        updateLog()
    }

    private fun loadRandomPup() = viewModelScope.launch {
        var randomPup = pupApi.randomPup()

        while (randomPup.imageUrl.contains(".mp4")) {
            randomPup = pupApi.randomPup()
        }
        _url.value = randomPup.imageUrl

        Log.i(tag, "loadRandomPup: ${_url.value}")
    }

    private fun updateLog() {
        _log.value += "Took puppy for a walk\n"
    }

}