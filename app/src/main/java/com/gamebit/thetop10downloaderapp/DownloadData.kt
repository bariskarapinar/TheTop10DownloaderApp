package com.gamebit.thetop10downloaderapp

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class DownloadData(private val callBack: DownloaderCallBack) : AsyncTask<String, Void, String>() {

    interface DownloaderCallBack {
        fun onDataAvailable(data: List<FeedEntry>)
    }

    override fun onPostExecute(result: String) {

        val parseApplications = ParseApplications()
        if (result.isNotEmpty()) {
            parseApplications.parse(result)
        }

        callBack.onDataAvailable(parseApplications.applications)
    }

    override fun doInBackground(vararg url: String): String {
        val rssFeed = downloadXML(url[0])
        if (rssFeed.isEmpty()) {
            Log.d("doInBackground", "Error Downloading")
        }
        return rssFeed
    }

    private fun downloadXML(urlPath : String): String {
        try {
            return URL(urlPath).readText()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
        return ""
    }
}