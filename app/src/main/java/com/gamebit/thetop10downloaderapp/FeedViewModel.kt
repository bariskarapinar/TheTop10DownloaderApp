package com.gamebit.thetop10downloaderapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

val EMPTY_FEED_LIST: List<FeedEntry> = Collections.emptyList()

class FeedViewModel : ViewModel(), DownloadData.DownloaderCallBack {
    private var downloadData: DownloadData? = null
    private var feedCachedUrl = "INVALIDATED"

    private val feed = MutableLiveData<List<FeedEntry>>()
    val feedEntries: LiveData<List<FeedEntry>>
        get() = feed

    init {
        feed.postValue(EMPTY_FEED_LIST)
    }

    fun downloadUrl(feedURL: String) {
        if (feedURL != feedCachedUrl) {
            downloadData = DownloadData(this)
            downloadData?.execute(feedURL)
            feedCachedUrl = feedURL
        } else {
            Log.d("URL Error", "URL Not Changed")
        }
    }

    fun invalidate() {
        feedCachedUrl = "INVALIDATE"
    }

    override fun onDataAvailable(data: List<FeedEntry>) {
        feed.value = data
    }

    override fun onCleared() {
        Log.d("onCleared", "Cancelling pending downloads")
        downloadData?.cancel(true)
    }
}
