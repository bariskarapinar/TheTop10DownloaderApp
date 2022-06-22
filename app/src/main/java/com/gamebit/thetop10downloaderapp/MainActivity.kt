package com.gamebit.thetop10downloaderapp

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val downloadData = DownloadData()
        downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
        Log.d("xxx", downloadData.toString());
    }

    companion object {
        private class DownloadData : AsyncTask<String, Void, String>() {
            override fun onPostExecute(result: String?) {
                Log.d("onPostExecute Data", result.toString())
                super.onPostExecute(result)
            }

            override fun doInBackground(vararg url: String?): String {
                val rssFeed = downloadXML(url[0])
                if (rssFeed.isEmpty()) {
                    Log.d("doInBackground", "Error Downloading")
                }
                return rssFeed
            }
        }

        private fun downloadXML(urlPath : String?): String{
            return URL(urlPath).readText()
        }
    }
}












