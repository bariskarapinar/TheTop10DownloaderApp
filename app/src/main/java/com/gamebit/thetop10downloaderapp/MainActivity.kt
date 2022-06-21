package com.gamebit.thetop10downloaderapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val downloadData = DownloadData()
        downloadData.execute("URL...")
    }

    companion object {
        private class DownloadData : AsyncTask<String, Void, String>() {
            override fun onPostExecute(result: String?) {
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
    }

    private fun downloadXML(urlPath : String?): String{
        val xmlResult = StringBuilder()
        try {
            val url = URL(urlPath)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val response = connection.responseCode
            Log.d("downloadXML", "responseCode$response")

            val inputStream = connection.inputStream
            val inputStreamReader = InputStreamReader(inputStream)
            val reader = BufferedReader(inputStreamReader)
        }catch (e: MalformedURLException) {
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
        }catch (e: Exception){
            e.printStackTrace()
        }


    }


}












